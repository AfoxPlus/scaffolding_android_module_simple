# Welcome to app-android-[name_module]!

![GithubActions](https://github.com/afoxplus/app-android-[name_module]/actions/workflows/android_publish.yml/badge.svg?branch=master) ![SonarCloud](https://sonarcloud.io/api/project_badges/measure?project=afoxplus-app-android-orders&metric=alert_status)

[name_module] is an library for [description_module]

## Setup

Create gradle.properties file in the root of your user's .gradle:

 ``` text 
 REPO_USERID_AFOXPLUS=****  
 REPO_TOKEN_AFOXPLUS=****  
 SONARCLOUDTOKEN=****   
 IS_LOCAL=true
 ```  

Run the following git commands:

```bash  
git submodule init
git submodule update
```  

## Usage

```kotlin  
dependencies {  
implementation("com.afoxplus.android:orders:$LAST_VERSION")
}  
```  

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)