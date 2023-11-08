#!/bin/bash
echo "Wellcome to Jarvis!"

# package name
echo "Insert your package name for project (Sample: com.afoxplus.orders): "
read PACKAGE

# project name
echo "Insert your name for project (Sample: orders, products, analytics, etc): "
#quedara asi: app-android-orders
read APPNAME


FULL_PROJECT_NAME="app-android-${APPNAME}"

# Change folder main
echo "Changing folder main project"
mkdir ../$FULL_PROJECT_NAME
mv ../scaffolding_android_module_simple/* ../scaffolding_android_module_simple/.[!.]* ../$FULL_PROJECT_NAME
rm -rf ../scaffolding_android_module_simple
echo "Done!"
echo "=================================================="


# Move folders
echo "Moving folders to new path"
SUBDIR=${PACKAGE//.//} # Replaces . with /
echo "app name: $FULL_PROJECT_NAME"
echo "$FULL_PROJECT_NAME/buildSrc/*"
for n in $(find ../$FULL_PROJECT_NAME -not -path "*/buildSrc/*" -type d \( -path '*/src/androidTest' -or -path '*/src/main' -or -path '*/src/test' \) )
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
find ../$FULL_PROJECT_NAME/ -type f -name "*.kt" -exec sed -i.bak "s/package com.afoxplus.module/package $PACKAGE/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f -name "*.kt" -exec sed -i.bak "s/import com.afoxplus.module.demo/import ${PACKAGE}.demo/g" {} \;
echo "Done!"
echo "=================================================="

# Gradle files
echo "Renaming *.kts files"
find ../$FULL_PROJECT_NAME/ -type f -name "*.kts" -exec sed -i.bak "s/com.afoxplus.module/$PACKAGE/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f -name "*.kts" -exec sed -i.bak "s/com.afoxplus.module.demo/${PACKAGE}.demo/g" {} \;
echo "Done!"
echo "=================================================="

# Rename app
echo "Renaming app to $FULL_PROJECT_NAME"
declare APPLICATION="${FULL_PROJECT_NAME}Application"
find ../$FULL_PROJECT_NAME/ -type f \( -name "settings.gradle.kts" -or -name "*.xml" \) -exec sed -i.bak "s/ThemeDemo/$APPNAME/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f \( -name "ConfigureApp.kt" \) -exec sed -i.bak "s/com.afoxplus.module/$PACKAGE/g" {} \;
find ../$FULL_PROJECT_NAME/ -name "MyApplication.kt" | sed "p;s/MyApplication/$APPLICATION/" | tr '\n' '\0' | xargs -0 -n 2 mv
echo "Done!"
echo "=================================================="

# Remove additional files
echo "Removing additional files"
#rm -rf ../$FULL_PROJECT_NAME/.git/
#rm -rf ../$FULL_PROJECT_NAME/jarvis.sh
#rm -rf ../$FULL_PROJECT_NAME/README.md
#rm -rf ../$FULL_PROJECT_NAME/CHANGELOG.md

# Clean file .bak
echo "Cleaning up"
find ../$FULL_PROJECT_NAME/ -name "*.bak" -type f -delete
echo "Done!"
echo "=================================================="

echo "Done!"