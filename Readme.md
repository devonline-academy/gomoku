# The Gomoku Project

-----------------------------------------------------------------------------------

## Without JRE

### Build instructions

- Build distributions using maven tool:

```bash
mvn -P without-jre clean package
```

- Use the following archives:
  - `target/gomoku-${project.version}-windows.zip` for Windows
  - `target/gomoku-${project.version}-unix.tar.gz` for MacOS or Linux

### Run instructions

- Download [OpenJDK 11](https://jdk.java.net/11/);
- Unzip the downloaded OpenJDK archive;
- Configure the `PATH` environment variable:
  - Add `%JDK_HOME%\bin\` directory for Windows;
  - Add `$JDK_HOME/bin/` directory for MacOS and Linux;
- Re-login or restart computer;
- Unzip the Gomoku distribution:
  - Unzip `gomoku-${project.version}-windows.zip` for Windows;
  - Unzip `gomoku-${project.version}-unix.tar.gz` for MacOS or Linux;
- Go to unzipped directory;
- Run the game by double-click on the start script:
  - `start*.cmd` for Windows;
  - `start*.sh` for MacOS or Linux;

-----------------------------------------------------------------------------------

## With JRE

### Build instructions

- Build distributions using maven tool:

```bash
mvn -P with-jre clean package
```

- Use the following archives:
  - `target/gomoku-${project.version}-windows-with-jre.zip` for Windows
  - `target/gomoku-${project.version}-macos-with-jre.tar.gz` for MacOS
  - `target/gomoku-${project.version}-linux-with-jre.tar.gz` for Linux

### Run instructions

- Unzip the Gomoku distribution:
  - Unzip `gomoku-${project.version}-windows-with-jre.zip` for Windows;
  - Unzip `gomoku-${project.version}-macos-with-jre.tar.gz` for MacOS;
  - Unzip `gomoku-${project.version}-linux-with-jre.tar.gz` for Linux;
- Go to unzipped directory;
- Run the game by double-click on the start script:
  - `start*.cmd` for Windows;
  - `start*.sh` for MacOS or Linux;

-----------------------------------------------------------------------------------

## Readme tutorial

- https://guides.github.com/features/mastering-markdown/
- https://help.github.com/categories/writing-on-github/
