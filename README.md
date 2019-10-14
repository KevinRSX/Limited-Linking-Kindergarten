# Limited Linked Kindergarten

## Setup of Workspace

Operations in this section happens only when you are trying to work on this project on a new device. All steps are performed in `master` branch.

Clone this repo:

```shell
git clone https://github.com/KevinRSX/Limited-Linking-Kindergarten
```

Then, open eclipse and use `your-path-to-Limited-Linking-Kindergarten/` (for example, `~/Desktop/Limited-Linking-Kindergarten`) as your eclipse workspace of this project.

Create a project and name it as `src`. DO NOT USE OTHER NAMES. Add JUnit5 and whatever libraries you need.

Move the `src/` folder outside the repository. Cut and paste the source code you have cloned from GitHub. Then, clean everything outside `src/src/` so that the repository look something like this.

```
Limited-Linking-Kindergarten/
|---.git/
|---.gitignore
|---README.md
|---docs/
		|---class-diagram
		|---gantt-chart
|---src/
		|---src/
				|---package1/
				|---package2/
		|---bin/
```

Note that `/src/bin` is already ignored by git.

Make sure every time you do some work, 

```
git pull
```

before you edit. Put your code in a seperate directory, do not include binary files.

Also check if there is any change at upstream during your work. If there is, also pull it down before committing.

I recommend you use [branch](https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging) and merge it with master locally

