LanguageMemoryApp
=================

Language Memory is a game where players learn about rare and exotic languages in the holdings of The Language Archive / Max-Planck-Institute for Psycholinguistics by listening to clips from the languages and matching clips of the same language. Combining a general interest in cultural diversity with a simple game interface has proven extremely popular with casual social gamers, as the popularity of ‘the Great Language Game’ has shown. This app improves these concepts by including a greater number of more diverse languages and collecting crucial data on players’ performance and linguistic backgrounds. The app offers links to more information on each language from a range of databases.

This project was started by merging the SynQuiz project as follows:

Added all the files from SynQuiz as a basis for this project. Hopefully all the history will be kept and any updates can be pulled as required.
This was done with:
git remote add synquizremote https://github.com/languageininteraction/GraphemeColourSynaesthesiaApp.git
git fetch synquizremote
git checkout -b synquiz synquizremote/master
git checkout master                
git merge synquiz

Then resolve any conflicts and push.

This process will leave all the synquiz tags and branches in the local copy. So it might be preferable to delete the checkout after this process and clone a fresh copy. This way human errors are less likely when pushing subsequent work. Subsequent merges can then be done by following the exact procedure again.
