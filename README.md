**Open Baato navigation from your app**
 
 
 To show **Baato Maps** to be listed in open with section when you want to navigate to Baato
 You need have this schema 

 
 ` val deepLink = "baato://?navigate=27.7032367,85.3296298&scheme=io.baato"`

And 

` val uri = Uri.parse(deepLink)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val chooserIntent = Intent.createChooser(intent, "Open with") 
     try {
         startActivity(chooserIntent)
        } catch (e: ActivityNotFoundException) {}  `
