#!/bin/bash
echo "Wellcome to Jarvis!"

# package name
echo "Insert your package name for project (Sample: com.afoxplus.orders): "
read PACKAGE

# project name
echo "Insert your name for project (Sample: app-android-orders): "
read APPNAME

# Change folder main
echo "Changing folder main project"
mkdir $APPNAME
mv ./scaffolding_android_module_simple/* ./scaffolding_android_module_simple/.[!.]* $APPNAME
rm -rf ./scaffolding_android_module_simple
echo "Done!"
echo "=================================================="

# Move folders
echo "Moving folders to new path"
SUBDIR=${PACKAGE//.//} # Replaces . with /
echo "app name: $APPNAME"
echo "$APPNAME/buildSrc/*"
for n in $(find ./$APPNAME -not -path "*/buildSrc/*" -type d \( -path '*/src/androidTest' -or -path '*/src/main' -or -path '*/src/test' \) )
do
  echo "Creating $n/java/$SUBDIR"
  mkdir -p $n/java/$SUBDIR
  echo "Moving files to $n/java/$SUBDIR"
  mv $n/java/com/afoxplus/module/* $n/java/$SUBDIR
  echo "Removing old $n/java/com/afoxplus/module"
  rm -rf mv $n/java/com/afoxplus/module
  echo "--------------------------------------------------"
done
echo "Done!"
echo "=================================================="


# Rename package and imports
echo "Renaming packages to $PACKAGE"
find $APPNAME/ -type f -name "*.kt" -exec sed -i.bak "s/package com.afoxplus.module/package $PACKAGE/g" {} \;
find $APPNAME/ -type f -name "*.kt" -exec sed -i.bak "s/import com.afoxplus.module.demo/import $PACKAGE/g" {} \;
echo "Done!"
echo "=================================================="

# Gradle files
echo "Renaming *.kts files"
find $APPNAME/ -type f -name "*.kts" -exec sed -i.bak "s/com.afoxplus.module/$PACKAGE/g" {} \;
echo "Done!"
echo "=================================================="

# Rename app
echo "Renaming app to $APPNAME"
declare APPLICATION="${APPNAME}Application"
find $APPNAME/ -type f \( -name "settings.gradle.kts" -or -name "*.xml" \) -exec sed -i.bak "s/AndroidTemplate/$APPNAME/g" {} \;
find $APPNAME/ -type f \( -name "ConfigureApp.kt" \) -exec sed -i.bak "s/com.afoxplus.module/$PACKAGE/g" {} \;
find $APPNAME/ -name "MyApplication.kt" | sed "p;s/MyApplication/$APPLICATION/" | tr '\n' '\0' | xargs -0 -n 2 mv
echo "Done!"
echo "=================================================="

# Remove additional files
echo "Removing additional files"
rm -rf $APPNAME/.git/
rm -rf $APPNAME/jarvis.sh
rm -rf $APPNAME/README.md
rm -rf $APPNAME/CHANGELOG.md

# Clean file .bak
echo "Cleaning up"
find $APPNAME/ -name "*.bak" -type f -delete
echo "Done!"
echo "=================================================="

echo "Done!"