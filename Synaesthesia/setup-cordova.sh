#mvn install
cd target
synquizname=synquiz-0.2-testing-SNAPSHOT
rm -rf $synquizname-phonegap
unzip $synquizname-phonegap.zip -d $synquizname-phonegap
cd $synquizname-phonegap 
cordova platform add ios
cordova platform add android
cordova plugin add https://github.com/danwilson/google-analytics-plugin.git

splashResourcesDir="./platforms/ios/SynQuiz/Resources/splash/"
echo $splashResourcesDir
splashImage="images/splash.png" #"images/splash.gif" "images/icon.jpg"
echo $splashImage
#file ./platforms/ios/SynQuiz/Resources/splash/*

echo "making iOS splash images"
convert -geometry 640x1136 -quality 100 $splashImage $splashResourcesDir/Default-568h@2x~iphone.png
convert -geometry 2048x1536 -quality 100 $splashImage $splashResourcesDir/Default-Landscape@2x~ipad.png
convert -geometry 1024x768 -quality 100 $splashImage $splashResourcesDir/Default-Landscape~ipad.png
convert -geometry 1536x2048 -quality 100 $splashImage $splashResourcesDir/Default-Portrait@2x~ipad.png
convert -geometry 768x1024 -quality 100 $splashImage $splashResourcesDir/Default-Portrait~ipad.png
convert -geometry 640x960 -quality 100 $splashImage $splashResourcesDir/Default@2x~iphone.png
convert -geometry 320x480 -quality 100 $splashImage $splashResourcesDir/Default~iphone.png

echo "making Android splash images"
convert -geometry 800x480 -quality 100 $splashImage platforms/android/res/drawable-land-hdpi/screen.png
convert -geometry 320x200 -quality 100 $splashImage platforms/android/res/drawable-land-ldpi/screen.png
convert -geometry 480x320 -quality 100 $splashImage platforms/android/res/drawable-land-mdpi/screen.png
convert -geometry 1280x720 -quality 100 $splashImage platforms/android/res/drawable-land-xhdpi/screen.png
convert -geometry 480x800 -quality 100 $splashImage platforms/android/res/drawable-port-hdpi/screen.png
convert -geometry 200x320 -quality 100 $splashImage platforms/android/res/drawable-port-ldpi/screen.png
convert -geometry 320x480 -quality 100 $splashImage platforms/android/res/drawable-port-mdpi/screen.png
convert -geometry 720x1280 -quality 100 $splashImage platforms/android/res/drawable-port-xhdpi/screen.png

# copy the ant.properties file with the android key store and alias (key.store= key.alias=) information so that the APK can be signed
cp ~/android-keys/ant.properties platforms/android/

echo "building"
#cordova prepare
#cordova compile
cordova build -release
# cordova emulate android
#cordova emulate ios --target="iPad"
#cordova emulate ios --target="iPhone"

# list the schemes available 
#xcodebuild -list
# build the iOS package
#cordova build ios -release
# sign the iOS package
#xcrun -sdk iphoneos PackageApplication -v "build/Release-iphoneos/MyApp.app" -o "build/Release-iphoneos/MyApp.ipa" --sign "iPhone Distribution: NAME (ID)" --embed "PROFILE_UUID.mobileprovision"
#xcodebuild -scheme YOURSCHEMENAME -project MyApp.xcodeproj -alltargets -sdk iphoneos7.0 PROVISIONING_PROFILE="PROFILE_UUID.mobileprovision" -configuration Release

#echo "launching xcode"
#open platforms/ios/SynQuiz.xcodeproj

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

