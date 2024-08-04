# Welcome to app-android-module_name!

![SonarCloud](https://sonarcloud.io/api/project_badges/measure?project=afoxplus-app-android-invitation&metric=alert_status)
![GithubActions](https://github.com/afoxplus/app-android-module_name/actions/workflows/android_build.yml/badge.svg?branch=master)
![GithubActions](https://github.com/afoxplus/app-android-module_name/actions/workflows/android_deploy.yml/badge.svg?branch=master)

module_name is an library for afoxplus.

## Setup

Create gradle.properties file in the root of your user's .gradle:

 ``` text 
 REPO_USERID_AFOXPLUS=****  
 REPO_TOKEN_AFOXPLUS=****  
 SONARCLOUDTOKEN=****   
 ```  

Run the following git commands:

```bash  
git submodule init
git submodule update
```  

## Usage

```kotlin  
dependencies {  
implementation("com.afoxplus.android:module_name:$LAST_VERSION")
}  
```  

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.

## Scan
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=afoxplus-app-android-module_name)

## License
[MIT](https://choosealicense.com/licenses/mit/)