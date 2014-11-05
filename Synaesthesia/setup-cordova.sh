#mvn install
cd target
rm -rf synaesthesia-0.1-testing-SNAPSHOT-phonegap
unzip synaesthesia-0.1-testing-SNAPSHOT-phonegap.zip -d synaesthesia-0.1-testing-SNAPSHOT-phonegap
cd synaesthesia-0.1-testing-SNAPSHOT-phonegap 
cordova platform add ios
cordova prepare

splashResourcesDir="./platforms/ios/SynQuiz/Resources/splash/"
echo $splashResourcesDir
splashImage="images/splash.png" #"images/splash.gif" "images/icon.jpg"
echo $splashImage
file ./platforms/ios/SynQuiz/Resources/splash/*

convert -geometry 640x1136 -quality 100 $splashImage $splashResourcesDir/Default-568h@2x~iphone.png
convert -geometry 2048x1536 -quality 100 $splashImage $splashResourcesDir/Default-Landscape@2x~ipad.png
convert -geometry 1024x768 -quality 100 $splashImage $splashResourcesDir/Default-Landscape~ipad.png
convert -geometry 1536x2048 -quality 100 $splashImage $splashResourcesDir/Default-Portrait@2x~ipad.png
convert -geometry 768x1024 -quality 100 $splashImage $splashResourcesDir/Default-Portrait~ipad.png
convert -geometry 640x960 -quality 100 $splashImage $splashResourcesDir/Default@2x~iphone.png
convert -geometry 320x480 -quality 100 $splashImage $splashResourcesDir/Default~iphone.png

open platforms/ios/SynQuiz.xcodeproj
