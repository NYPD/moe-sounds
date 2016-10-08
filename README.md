# moe-sounds
Moe web app. Moe sounds, moe problems.

## Project Configuration For Noobs

Skip whichever section does not apply to you.

#### Git Configuration
1.  Install the GUI friendly GitHub Desktop: https://desktop.github.com/
2. Go Tools and Options (the cog icon on the top right) and click **Options**
3. Change the **Clone Path** to your desired development directory: e.g. `C:\Development\git`
4. Under **Accounts**, click **Add account** and log into your GitHub account

#### Node/Grunt Configuration
1. Install node.js: https://nodejs.org/en/
2. Open up your favorite terminal (CMD or cygwin) and install the **Grunt command line** using `npm install -g grunt-cli` in the terminal
3. Navigate to the root project directory in the terminal e.g. `cd C:\Development\git\moe-sounds`
4. Run `npm install` to install all the project dependencies (this should create a node_modules folder in the root folder)
  * Don't worry about committing this, the .gitignore file should ignore this directory
5. Run `grunt watch` in the root directory to kick of the grunt watch plugin, this is what auto compiles and moves files around as per the Gruntfile.js configuration
  * This step has to be run every time you work on the project (if you want it to auto compile)

#### Java Configuration
1. Install the latest version of [Java](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

#### Tomcat  Configuration
1. Download the latest zip (Windows zip) stable version of [Tomcat](https://tomcat.apache.org/download-80.cgi)
2. Extract the zip to your desired location e.g. `C:\Development\Tomcat\apache-tomcat-8.0.32`

#### Eclipse Configuration
1. Install **Eclipse IDE for Java EE Developers**: http://www.eclipse.org/downloads/
2. Extract the zip folder to your desired location e.g. `C:\Users\NYPD\AppData\Local\Eclipse Mars`
3. Run  **eclipse.exe** (probably want to create a shortcut for this bad boy)
4. Choose whichever project workspace location you want e.g. `C:\Development\Eclipse Mars Workspace`
5. Once eclipse started up, go to **Help** and click **Install New Software**
6. Under **Work With** select `M2E - http://download.eclipse.org/technology/m2e/releases/`
7. Click the **Maven Integration for Eclipse** checkbox and install that
8. Next go to **Window** / **Preferences** / **Server** / **Runtime Environments**
9. Click on **Add...** and select **Apache / Apache Tomcat v8.0**. Click next and for installation directory select the place you extracted tomcat to e.g. `'C:\Development\Tomcat\apache-tomcat-8.0.32`. Click finish
10. If you want to be dark and edgy install this: http://marketplace.eclipse.org/content/eclipse-moonrise-ui-theme and go to **Window** / **Preferences** / **General** / **Appearance** and select MoonRise (standalone) as you main theme


#### Import Project into Eclipse
1. In the **Project Explorer** section of eclipse, right click and click **Import > Import...**
2. Filter for **Existing Maven Projects** and click next
3. For root directory choose wherever GitHub Desktop cloned the project into e.g. `C:\Development\git\moe-sounds` and click finish
4. Right click your tomcat server in the **Servers** tab and click on **Add and Remove...** and add the **moe-apps** project to the configured side and click **Finish**
5. Now double click the tomcat server to open up the Overview window. Go to the Modules tab and click **Edit...** on the moe-apps entry and delete everything inside the **Path:** input box then click ok. Then press `Ctrl + S` to save the tomcat configuration
6. Now just right click the tomcat server and click on **Start**. Then open up your netscape browser and go to 127.0.0.1:8080 to access the moe-app website
