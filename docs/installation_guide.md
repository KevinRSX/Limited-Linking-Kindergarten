# Limited Linking Kindergarten -- Installation Guide

## Install From Binary

### Prerequisite

We have only tested our program on the systems listed below. You may run it on other systems, but we do not maintain it on these systems and they are not guaranteed to be bug-free.

- Operating System: Windows 10, MacOS 10.14 or above, Ubuntu 16.04 LTS, Ubuntu 18.04 LTS

- Java Runtime Environment (JRE): JRE 10

- (Optional) If you would like to build from source, you need to install Git

- Java is added to system path:

  - [Windows](https://docs.alfresco.com/4.2/tasks/fot-addpath.html)

  - [MacOS and Linux](https://www.google.com/search?sxsrf=ACYBGNQXhTkX0fp-YUb8TSlAAgOB0_D-VQ%3A1575814406551&ei=BgXtXdanIZaC-QaQ2aPoBA&q=add+environment+variable+mac&oq=add+environment+variable+mac&gs_l=psy-ab.3...983.1446..1744...0.0..0.0.0.......0....1..gws-wiz.oRrx560c-3I&ved=0ahUKEwjWwryPnqbmAhUWQd4KHZDsCE0Q4dUDCAs&uact=5)

    

### Installation

#### Online Release (TODO: fill this in)

Download the online release here: https://github.com/KevinRSX/Limited-Linking-Kindergarten/releases

Decompress `llk-release3.zip`. Go to the corresponding folder and enjoy the game!

```shell
cd llk-release3/
java -jar llk-release3.jar
```



#### Offline Configuration

Find the binary file `llk-release3.jar`, place image and data folders under the same folder with it, such as:

```
some_dir/
|---llk-release3.jar
|---image/
    |---pic1.png
    |---background.png
    |---other_images
|---data/
		|---test1.txt
		|---other_data
```

Then go to this directory and run the `.jar` file:

```shell
cd path/to/some_dir/
java -jar llk-release3.jar
```



## Install From Source 

You may also download the source code from GitHub or this folder.

```
git clone https://github.com/KevinRSX/Limited-Linking-Kindergarten/
```

Create a Java project in eclipse of other IDEs (such as IntelliJ) that support Java.

Place the codes in `src` folder to your `src` folder in the created Java project. Also place `image` and `data` folder outside `src` folder. It should be something like this:

```
llk (project name)/
|---src/
    |---game/
    |---gui/
    |---other_packages/
|---image/
    |---pic1.png
    |---background.png
    |---other_images
|---data/
    |---test1.txt
    |---other_data
|---bin/
```

Use JRE 10 to compile and run the project.