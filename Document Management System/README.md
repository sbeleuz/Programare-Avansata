Laboratorul 5

Compulsory (week 5):\
-Class Document;\
-Class Catalog;\
-Class CatalogUtil (methods: save, load, view).

Optional & Bonus (week 6):
- saveAsText and loadAsText methods in CatalogUtill to save/load catalog as plain text, using toPlainText method in Catalog and Document to represent them;
- Class Shell - read commands and parameters from keyboard and implements commands (each is represented by a new class derived from Class Command): LoadCommand, ViewCommand, ListCommand, ReportCommand (create a html report and a Microsoft Word report, using Apache POI), InfoCommand (display metadata of a file, using Apache Tika);
- custom exceptions: InvalidCatalogException (used when a catalog's path can't be be loaded), InvalidDocumentException (used when a catalog location (path or URL is not valid, for URL checking I used an UrlValidator), InvalidCommandException (used when user give an unknown command to shell);
- lab5.jar - executable JAR of application.
