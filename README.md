LanguageMemoryApp
=================

Language Memory is a game where players learn about rare and exotic languages in the holdings of The Language Archive / Max-Planck-Institute for Psycholinguistics by listening to clips from the languages and matching clips of the same language. Combining a general interest in cultural diversity with a simple game interface has proven extremely popular with casual social gamers, as the popularity of ‘the Great Language Game’ has shown. This app improves these concepts by including a greater number of more diverse languages and collecting crucial data on players’ performance and linguistic backgrounds. The app offers links to more information on each language from a range of databases.

This project was started by merging the SynQuiz project as follows:

Added all the files from SynQuiz as a basis for this project. Hopefully all the history will be kept and any updates can be pulled as required.
This was done with:
git remote add synquizmaster https://github.com/languageininteraction/GraphemeColourSynaesthesiaApp.git
git fetch synquizmaster
git checkout -b synquizmaster synquizmaster/master
git checkout master                
git merge synquizmaster

But also some additional changes were pulled in before the actual merge:
git checkout synquizmaster
git pull
git checkout master 

Perhaps the branch name and the remote name could have been better chosen: synquizremote and synquiz might have been better choices.