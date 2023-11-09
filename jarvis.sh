#!/bin/bash
echo "Wellcome to Jarvis!"

echo "Insert your name for module (Examples: orders, products, analytics, etc): "
read MODULE_NAME

FULL_PROJECT_NAME="app-android-${MODULE_NAME}"
FUL_PACKAGE_NAME="com.afoxplus.${MODULE_NAME}"

# Change folder main
#echo "Changing folder main project"
#mkdir ../$FULL_PROJECT_NAME
#mv ../scaffolding_android_module_simple/* ../scaffolding_android_module_simple/.[!.]* ../$FULL_PROJECT_NAME
#rm -rf ../scaffolding_android_module_simple
#echo "Done!"
#echo "=================================================="

# Move folders
echo "Moving folders to new path"
SUBDIR=${FUL_PACKAGE_NAME//.//} # Replaces . with /
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

# Rename Package & Imports
echo "Renaming packages & imports to $FUL_PACKAGE_NAME"
find ../$FULL_PROJECT_NAME/ -type f -name "*.kt" -exec sed -i.bak "s/package com.afoxplus.module/package $FUL_PACKAGE_NAME/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f -name "*.kt" -exec sed -i.bak "s/import com.afoxplus.module.demo/import ${FUL_PACKAGE_NAME}.demo/g" {} \;
echo "Done!"

# Rename Gradle files
echo "Renaming gradle files to $FULL_PROJECT_NAME"
find ../$FULL_PROJECT_NAME/ -type f -name "*.kts" -exec sed -i.bak "s/com.afoxplus.module/$FUL_PACKAGE_NAME/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f -name "*.kts" -exec sed -i.bak "s/com.afoxplus.module.demo/${FUL_PACKAGE_NAME}.demo/g" {} \;
echo "Done!"

# Rename Classes
echo "Renaming classes to $FULL_PROJECT_NAME"
declare APPLICATION="${FULL_PROJECT_NAME}Application"
find ../$FULL_PROJECT_NAME/ -type f \( -name "*.xml" \) -exec sed -i.bak "s/module/$MODULE_NAME/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f \( -name "ConfigureApp.kt" \) -exec sed -i.bak "s/module/$MODULE_NAME/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f \( -name "README.md" \) -exec sed -i.bak "s/module_name/$MODULE_NAME/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f \( -name "CHANGELOG.md" \) -exec sed -i.bak "s/module_name/$MODULE_NAME/g" {} \;
find ../$FULL_PROJECT_NAME/ -type f \( -name "ExampleInstrumentedTest.kt" \) -exec sed -i.bak "s/module/$MODULE_NAME/g" {} \;
echo "Done!"

# Remove additional files
echo "Removing additional files"
#rm -rf ../$FULL_PROJECT_NAME/.git/
rm -rf ../$FULL_PROJECT_NAME/jarvis.sh
#rm -rf ../$FULL_PROJECT_NAME/README.md
#rm -rf ../$FULL_PROJECT_NAME/CHANGELOG.md

# Clean file .bak
echo "Cleaning up"
find ../$FULL_PROJECT_NAME/ -name "*.bak" -type f -delete
echo "Your project name is: ${FULL_PROJECT_NAME}"
echo "Your package name is: ${FUL_PACKAGE_NAME}"
echo "Done!"