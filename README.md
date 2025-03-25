<p align="center">
<img align="center" width="128" src="./.github/flint.png" alt="Flint Icon"/>
</p>
<h1 align="center">Flint</h1>

<p align="center">
Core library for DiamondFire mods, allowing them to share a common codebase and systems.
</p>

---

> [!WARNING]  
> README is unfinished and may contain incorrect, outdated, or missing information.

## Features

### Feature Management

Mods can define features, classes that implement a **FeatureTrait**.

#### Feature Traits

- **ChatListeningFeature** - Listen to chat messages
- **CommandFeature** - Register a command with aliases
- **ModeSwitchListeningFeature** - Listen to mode switches
- **PacketListeningFeature** - Listen to incoming and outgoing packets
- **PlotSwitchListeningFeature** - Listen to plot switches
- **RenderedFeature** - Run code when a frame is rendered
- **ShutdownFeature** - Run code when the game is shutting down
- **TickedFeature** - Run code every tick
- **TooltipRenderFeature** - Run code when a tooltip is rendered, allowing you to modify it
- **UserCommandListeningFeature** - Run code when the player runs a command, lets you to modify or cancel the command
- **UserMessageListeningFeature** - Run code when the player sends a message, lets you to modify or cancel the message
- **WorldRenderFeature** - Listen to world render events

#### Registering features

To register a feature, in your mod's onInitializeClient,
call **FlintAPI.registerFeature(FeatureTrait)** or **FlintAPI.registerFeature(FeatureTrait...)**.

```java
public class Flint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FlintAPI.registerFeatures(
                new AwesomeFeature()
        );

    }

}
```

### Message System

Send formatted messages with ease using messages.

```java
public void success() {

    Flint.getUser().sendMessage(new CompoundMessage(
            new SuccessMessage("my_mod.awesome_success"),
            new SoundMessage(FSound.builder()
                    .setSound(SoundEvents.ENTITY_PLAYER_LEVELUP)
                    .setPitch(1.5F)
                    .build()
            )
    ));

}
```

### Color Palette

The library provides a color palette for easy access to beautiful colors.

```java
public void error() {

    Component.text("You did something BAD!", PaletteColor.RED);

}
```

### Mode Tracking

Flint tracks the user's current mode and optionally the plot and node they are on, you can access this info using
**Flint.getUser().getMode()**, **Flint.getUser().getPlot()**, and **Flint.getUser().getNode()**.

```java
public boolean shouldShowPlotOverlay() {
    return Flint.getUser().getMode() == Mode.PLAY && Flint.getUser().getPlot().handle() == "myplot";
}
```

> [!IMPORTANT]  
> You only have access to the user's plot if **FlintAPI.shouldConfirmLocationWithLocate()** was called, if you are not
> calling this on your own, remember that you will get null when calling **Flint.getUser().getPlot()**!
>
> This makes Flint run /locate every time a suspected mode change occurs.
> Not only does this give you access to the plot the user is on, but it also makes mode tracking more accurate.

### DFItem

TODO

### Useful Classes

#### Mode

An enum containing every mode the user can be in,
you can check whether a mode means the user is in a plot
or if they are in a mode that allows them to modify a plot with `Mode.isInPlot()` and `Mode.isEditor()` respectively.

#### Plot

A record that stores surface level information about a plot, such as the plot's name, id, and handle.

#### Node

An enum of every node on DiamondFire, you can get a node's ID with `Node.getId()` or its name with `Node.getName()`. Get
a node by its id or name using `Node.fromId()` or `Node.fromName()` respectively.

#### Toaster

Lets you send toasts with `Toaster.toast(Component title, Component message)`.

#### RateLimiter

A rate limiter that can be used to limit the rate at which code is executed.

## How to install

> [!NOTE]
> We are planning to switch to rolling releases on all platforms.

Either grab the latest release from [the releases page](https://github.com/dFOnline/flint/releases/latest) or
on [Modrinth](https://modrinth.com/mod/flint).

## Depending on Flint

Use the Modrinth Maven repository to depend on Flint in your project.

### What version?
Flint updates on a **rolling release** schedule, once a push is made to the main branch, a new version is released.
The latest release will be the most recent GitHub release, or other most recent release on Modrinth.

```gradle
repositories {
    // This is the recommended way to add the Modrinth Maven repository.
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = "https://api.modrinth.com/maven"
            }
        }
        filter {
            includeGroup "maven.modrinth"
        }
    }
}

dependencies {
    // Use modApi to gain access to Flint's classes.

    // Flint
    modApi("maven.modrinth:flint:${project.flint_version}")

    // Flint depends on Adventure,
    // loom doesn't resolve dependencies of dependencies
    // so you need to include it in your project yourself.

    // Adventure
    modImplementation include("net.kyori:adventure-platform-fabric:${project.adventure_fabric_version}") {
        exclude group: "net.fabricmc.fabric-api"
    }

}
```

> [!TIP]
> Don't include Flint in your mod jar, users should get Flint themselves so it can be updated independently.

