# Chisels & Bits 2
A successor to [AlgorithmX2's original Chisels & Bits](https://github.com/AlgorithmX2/Chisels-and-Bits) that had releases for Minecraft 1.8 through 1.12. A Minecraft mod about chiseling, sculpting and designing custom blocks made of various materials, fluids or colours.

Download
--------------
You can download beta or release builds of [~~Chisels & Bits 2 on Curseforge!~~](https://www.curseforge.com/minecraft/mc-mods/chisels-bits-2)
_link won't work until the first build has been released on curseforge_

Alternatively, alpha/snapshot builds are available on the [GitHub releases page](https://github.com/Aeltumn/Chisels-and-Bits-2/releases).

Differences from the original mod
--------------
Chisels & Bits 2 was remade almost completely and all features in the mod were reevaluated and often reimplemented slightly differently. [You can read up on the original planned changes here.](DIFFERENCES.md)
You can also take a look at [the roadmap](ROADMAP.md) where you can read up on what features are going to be worked on next and what needs to be done.

Contributing
--------------
If you'd like to contribute something to Chisels & Bits, you're free to do so. However, not all changes will be accepted. If you're unsure if your suggestion fits the mod, [open an issue](https://github.com/Aeltumn/Chisels-and-Bits-2/issues) to discuss it first!

Compilation
--------------
1) Clone this repository and check out the branch of the version you want to build. (1.14.x, 1.15.x)
2) Load it into an IDE of your choice and import the project.
3) Run `build` using gradle
4) You'll find the built jar in the `/build/libs/` folder.

Extra steps if you want to setup a C&B development environment:
5) Create an empty file called `settings.gradle` file and enter the path to the built C&B jar to it as key "gradle.ext.buildJar". The file should contain something similar to this:
```gradle.ext.buildJar = "C:\\Chisels-and-Bits-2\\build\\libs\\chiselsandbits2-0.5.1a.jar"```
6) Run `genIntellijRuns` or `genEclipseRuns` depending on your IDE used.
7) Run `runClient` to start the mod.