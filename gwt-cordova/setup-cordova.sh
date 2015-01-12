#mvn install
cd target
synquizname=synquiz-1.0.64-testing
rm -rf $synquizname-cordova
unzip $synquizname-cordova.zip -d $synquizname-cordova
cd $synquizname-cordova
cordova platform add ios
cordova platform add android
cordova plugin add https://github.com/danwilson/google-analytics-plugin.git
cordova plugin add org.apache.cordova.device
cordova plugin add org.apache.cordova.statusbar
cordova plugin add org.apache.cordova.splashscreen
cordova plugin add org.apache.cordova.inappbrowser
# (this plugin did not function correctly) cordova plugin add https://github.com/leecrossley/cordova-plugin-social-message.git
cordova plugin add https://github.com/EddyVerbruggen/SocialSharing-PhoneGap-Plugin.git #cordova plugin add nl.x-services.plugins.socialsharing
#cordova plugin add https://github.com/driftyco/ionic-plugins-keyboard.git # this provides events for keyboard hide and show which are needed to resize the app window area
#cordova plugin add https://github.com/etiennea/phonegap-keyboard.git
#cordova plugin install org.apache.cordova.labs.keyboard
cordova plugin add https://github.com/GetJobber/cordova-plugin-keyboard.git

splashResourcesDir="./platforms/ios/SynQuiz/Resources/splash/"
echo $splashResourcesDir
splashImage="images/splash.png" #"images/splash.gif" "images/icon.jpg"
echo $splashImage
#file ./platforms/ios/SynQuiz/Resources/splash/*

echo "making 9 patch splash images"
convert -background none images/LiI_logo_rgb.jpg -resize 320x320 -matte -bordercolor "rgb(0,158,200)" -border 2 -fill black -draw "line 1,0 1,0" -draw "line 0,1 0,1" -draw "line 0,67 0,67" -draw "line 322,0 322,0" platforms/splash320x320.9.png
#draw9patch platforms/splash320x320.9.png
convert -background none images/LiI_logo_rgb.jpg -resize 150x150 -matte -bordercolor "rgb(0,158,200)" -border 2 -fill black -draw "line 1,0 1,0" -draw "line 0,1 0,1" -draw "line 0,32 0,32" -draw "line 152,0 152,0" platforms/splash150x150.9.png
#draw9patch platforms/splash150x150.9.png

echo "making iOS splash images"
#convert -resize 640x1136^ -gravity center -extent 640x1136 -quality 100 $splashImage $splashResourcesDir/Default-568h@2x~iphone.png
convert -resize 1136x1136^ -gravity center -extent 640x1136 -quality 100 $splashImage $splashResourcesDir/Default-568h@2x~iphone.png
convert -resize 2048x2048^ -gravity center -extent 2048x1536 -quality 100 $splashImage $splashResourcesDir/Default-Landscape@2x~ipad.png
convert -resize 1024x1024^ -gravity center -extent 1024x768 -quality 100 $splashImage $splashResourcesDir/Default-Landscape~ipad.png
convert -resize 2048x2048^ -gravity center -extent 1536x2048 -quality 100 $splashImage $splashResourcesDir/Default-Portrait@2x~ipad.png
convert -resize 1024x1024^ -gravity center -extent 768x1024 -quality 100 $splashImage $splashResourcesDir/Default-Portrait~ipad.png
convert -resize 960x960^ -gravity center -extent 640x960 -quality 100 $splashImage $splashResourcesDir/Default@2x~iphone.png
convert -resize 480x480^ -gravity center -extent 320x480 -quality 100 $splashImage $splashResourcesDir/Default~iphone.png

echo "making Android splash images"
#cp platforms/splash320x320.9.png platforms/android/res/drawable-land-hdpi/screen.png
#cp platforms/splash150x150.9.png platforms/android/res/drawable-land-ldpi/screen.png
#cp platforms/splash150x150.9.png platforms/android/res/drawable-land-mdpi/screen.png
#cp platforms/splash320x320.9.png platforms/android/res/drawable-land-xhdpi/screen.png
#cp platforms/splash320x320.9.png platforms/android/res/drawable-port-hdpi/screen.png
#cp platforms/splash150x150.9.png platforms/android/res/drawable-port-ldpi/screen.png
#cp platforms/splash150x150.9.png platforms/android/res/drawable-port-mdpi/screen.png
#cp platforms/splash320x320.9.png platforms/android/res/drawable-port-xhdpi/screen.png

convert -resize 800x480^ -gravity center -extent 800x480 -quality 100 $splashImage platforms/android/res/drawable-land-hdpi/screen.png
convert -resize 320x200^ -gravity center -extent 320x200 -quality 100 $splashImage platforms/android/res/drawable-land-ldpi/screen.png
convert -resize 480x320^ -gravity center -extent 480x320 -quality 100 $splashImage platforms/android/res/drawable-land-mdpi/screen.png
convert -resize 1280x720^ -gravity center -extent 1280x720 -quality 100 $splashImage platforms/android/res/drawable-land-xhdpi/screen.png
convert -resize 480x800^ -gravity center -extent 480x800 -quality 100 $splashImage platforms/android/res/drawable-port-hdpi/screen.png
convert -resize 200x320^ -gravity center -extent 200x320 -quality 100 $splashImage platforms/android/res/drawable-port-ldpi/screen.png
convert -resize 320x480^ -gravity center -extent 320x480 -quality 100 $splashImage platforms/android/res/drawable-port-mdpi/screen.png
convert -resize 720x1280^ -gravity center -extent 720x1280 -quality 100 $splashImage platforms/android/res/drawable-port-xhdpi/screen.png

# copy the ant.properties file with the android key store and alias (key.store= key.alias=) information so that the APK can be signed
cp ~/android-keys/ant.properties platforms/android/

echo "building"
#cordova prepare
#cordova compile
cordova build -release
#cordova emulate ios --target="iPad"
#cordova emulate ios --target="iPhone"
#cordova emulate android 

# list the schemes available 
#xcodebuild -list
# build the iOS package
#cordova build ios -release
# sign the iOS package
#xcrun -sdk iphoneos PackageApplication -v "build/Release-iphoneos/MyApp.app" -o "build/Release-iphoneos/MyApp.ipa" --sign "iPhone Distribution: NAME (ID)" --embed "PROFILE_UUID.mobileprovision"
#xcodebuild -scheme YOURSCHEMENAME -project MyApp.xcodeproj -alltargets -sdk iphoneos7.0 PROVISIONING_PROFILE="PROFILE_UUID.mobileprovision" -configuration Release

#echo "installing on Android"
#adb install target/*-testing-cordova/platforms/android/ant-build/CordovaApp-release.apk

echo "launching xcode"
open platforms/ios/SynQuiz.xcodeproj&

# generate the IPA
#cd platforms/ios/CordovaLib/
#pwd
#xcodebuild -alltargets -project CordovaLib.xcodeproj -sdk iphoneos -configuration Release
#xcodebuild -scheme CordovaLib -project CordovaLib.xcodeproj -sdk iphoneos -configuration Release

#cd ..
#pwd
#xcodebuild -alltargets -project SynQuiz.xcodeproj -sdk iphoneos -configuration Release
#xcodebuild -scheme SynQuiz -project SynQuiz.xcodeproj -sdk iphoneos -configuration Release
#cd build/Release-iphoneos
cd platforms/ios/
cd build/emulator
pwd
xcrun -sdk iphoneos PackageApplication -v "$(pwd)/SynQuiz.app" -o "$(pwd)/$synquizname.ipa"

# validate the results
xcrun -verbose -sdk iphoneos Validation "$(pwd)/$synquizname.ipa"

pwd
cd ../../../../
# list the APK files that have been built
find platforms/ -iname *.apk

# list the IPA files that have been built
find platforms/ -iname *.ipa

