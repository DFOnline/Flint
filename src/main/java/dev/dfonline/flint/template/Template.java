package dev.dfonline.flint.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dfonline.flint.data.DFItem;
import dev.dfonline.flint.template.block.BaseBlock;
import dev.dfonline.flint.template.block.CodeBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class Template {

    private static final int GZIP_BUFFER_SIZE = 1024;
    private String name;
    private String author;
    private final int version = 1;
    private String code;

    @JsonIgnore
    private List<CodeBlock> blocks;

    public Template() {
        this.blocks = new ArrayList<>();
    }

    public Template(String name, String author) {
        this();
        this.name = name;
        this.author = author;
    }

    public Template(String name, String author, List<CodeBlock> blocks) {
        this(name, author);
        this.blocks = blocks;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCode() {
        this.updateCodeFromBlocks();
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
        this.updateBlocksFromCode();
    }

    @JsonIgnore
    public List<CodeBlock> getBlocks() {
        return this.blocks;
    }

    public void setBlocks(List<CodeBlock> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(CodeBlock block) {
        this.blocks.add(block);
    }

    public void removeBlock(int index) {
        if (index >= 0 && index < this.blocks.size()) {
            this.blocks.remove(index);
        }
    }

    public void updateCodeFromBlocks() {
        if (this.blocks != null) {
            try {
                TemplateData templateData = new TemplateData(this.blocks);
                ObjectMapper mapper = createObjectMapper();
                String json = mapper.writeValueAsString(templateData);

                System.out.println("json: " + json);

                // Compress using GZip
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
                    gzipOutputStream.write(json.getBytes(StandardCharsets.UTF_8));
                }

                // Convert to Base64
                this.code = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                throw new RuntimeException("Failed to encode template data", e);
            }
        }
    }

    private void updateBlocksFromCode() {
        if (this.code != null && !this.code.isEmpty()) {
            try {
                // Decode Base64
                byte[] compressedData = Base64.getDecoder().decode(this.code);

                // Decompress GZip
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
                    byte[] buffer = new byte[GZIP_BUFFER_SIZE];
                    int len;
                    while ((len = gzipInputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, len);
                    }
                }

                String json = byteArrayOutputStream.toString(StandardCharsets.UTF_8);

                // Deserialize JSON
                ObjectMapper mapper = createObjectMapper();
                TemplateData templateData = mapper.readValue(json, TemplateData.class);
                this.blocks = templateData.getBlocks();
            } catch (IOException e) {
                throw new RuntimeException("Failed to decode template data", e);
            }
        }
    }

    public String toJson() {
        try {
            ObjectMapper mapper = createObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize template", e);
        }
    }

    public static Template fromItem(ItemStack item) throws RuntimeException {
        DFItem dfItem = new DFItem(item);
        if (!dfItem.hasHypercubeKey("codetemplatedata")) {
            throw new RuntimeException("Item doesnt have templatedata");
        }
        return fromJson(dfItem.getHypercubeStringValue("codetemplatedata"));
    }

    public static Template fromJson(String json) {
        try {
            ObjectMapper mapper = createObjectMapper();
            return mapper.readValue(json, Template.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize template", e);
        }
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    public ItemStack toItem(Text itemName, Item material) {
        return this.toItem(itemName, new ItemStack(material));
    }

    public ItemStack toItem(Text itemName, ItemStack stack) {
        DFItem dfItem = DFItem.of(stack);
        dfItem.getPublicBukkitValues().setHypercubeStringValue("codetemplatedata", this.toJson());
        dfItem.setName(itemName);
        return dfItem.getItemStack();
    }

    public enum EqualsScope {
        META,
        BLOCK_SIZE,
        BLOCK_TYPES,
        BLOCK_ACTIONS,
        ARGUMENTS;

        public static final EqualsScope[] ALL = EqualsScope.values();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Template other)) {
            return false;
        }

        return this.equals(other, EqualsScope.META) == null;
    }

    public EqualsScope equals(Template other, EqualsScope... scopes) {
        Set<EqualsScope> scopeSet = Set.of(scopes);

        if (other == null) {
            return EqualsScope.META;
        }

        if (scopeSet.contains(EqualsScope.META) && (!this.name.equals(other.name) ||
                !this.author.equals(other.author))) {
            return EqualsScope.META;
        }

        if (scopeSet.contains(EqualsScope.BLOCK_SIZE) &&
                this.blocks.size() != other.blocks.size()) {
            return EqualsScope.BLOCK_SIZE;
        }

        for (int i = 0; i < this.blocks.size(); i++) {
            CodeBlock thisBlock = this.blocks.get(i);
            CodeBlock otherBlock = other.blocks.get(i);

            if (scopeSet.contains(EqualsScope.BLOCK_TYPES) &&
                    !thisBlock.getId().equals(otherBlock.getId())) {
                return EqualsScope.BLOCK_TYPES;
            }

            if (scopeSet.contains(EqualsScope.BLOCK_ACTIONS) &&
                    thisBlock instanceof BaseBlock thisBase &&
                    otherBlock instanceof BaseBlock otherBase) {
                if (!thisBase.getAction().equals(otherBase.getAction())) {
                    return EqualsScope.BLOCK_ACTIONS;
                }
            }

            if (scopeSet.contains(EqualsScope.ARGUMENTS) &&
                    thisBlock instanceof BaseBlock thisBase &&
                    otherBlock instanceof BaseBlock otherBase) {
                if (thisBase.getArguments() == null) {
                    if (otherBase.getArguments() != null) {
                        return EqualsScope.ARGUMENTS;
                    }
                } else if (!thisBase.getArguments().equals(otherBase.getArguments())) {
                    return EqualsScope.ARGUMENTS;
                }
            }
        }

        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.author, this.blocks);
    }

    private static class TemplateData {
        private List<CodeBlock> blocks;

        private TemplateData() {
        }

        TemplateData(List<CodeBlock> blocks) {
            this.blocks = blocks;
        }

        public List<CodeBlock> getBlocks() {
            return this.blocks;
        }

        public void setBlocks(List<CodeBlock> blocks) {
            this.blocks = blocks;
        }
    }

}
