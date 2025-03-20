# Flint

Core library for DiamondFire mods, allowing them to share a common codebase and systems.

> WIP README!

## Features

### Feature Management

Mods can define features, classes that implement a **FeatureTrait**.

#### Feature Traits

- **CommandFeature** - Lets a feature register a command with aliases.
- **PacketListeningFeature** - Lets a feature listening to incoming and outgoing packets
- **RenderedFeature** - Lets a packet run code when a frame is rendered
- **TickableFeature** - Lets a feature run code every tick

#### Registering features

To register a feature, in your mod's onInitializeClient, listen to the feature registration event and return a list of
your features.

```java
public class Flint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FeatureRegistrationCallback.EVENT.register(() -> List.of(
                new AwesomeFeature()
        ));
    }

}
```

### Message System

Send formatted messages with ease using messages.

```java
Flint.getUser().

sendMessage(new CompoundMessage(
        new SuccessMessage("my_mod.awesome_success"),
        new SoundMessage(FSound.builder()
                .soundEvent(SoundEvents.ENTITY_PLAYER_LEVELUP)
                .pitch(1.5F)
                .build()
        )
));
```

### Color Palette

The library provides a color palette for easy access to beautiful colors.

```java
Component.text("You did something BAD!",PaletteColor.RED);
```

### Mode Tracking

...

### Callbacks

...

## How to install

...

## Depending on Flint

...