
# Documentation Για Android Studio Project
<br>
[cookEat APK](https://github.com/antsinar/AAY/releases/tag/pre-release)<br>
<br>
#### Για την υλοποίηση χρειάστηκαν οι εξής πηγές από το internet<br>
- [Documentation Retrofit](https://square.github.io/retrofit/), γραμμένη δυστυχώς για Java<br>
- [Official Android Developer](https://developer.android.com)<br>
- [Retrofit Tutorial στο Youtube](https://www.youtube.com/watch?v=5gFrXGbQsc8)<br>
- [Retrofit to RecyclerView Tutorial στο Youtube](https://www.youtube.com/watch?v=_bVWsL5CHh4)<br>
- [RecyclerView Tutorial](https://www.raywenderlich.com/1560485-android-recyclerview-tutorial-with-kotlin)<br>
- [Intent tutorial στο Youtube](https://www.youtube.com/watch?v=wKFJsrdiGS8)<br>
- Stack Overflow, αν και οι περισσότερες λύσεις απευθύνονταν σε Java<br>
- Και η βοήθεια του κ.Καρασούλα<br>

> Τα αρχεία και τα κομμάτια κώδικα που δεν αναφέρονται εν τέλει δεν χρησιμοποιήθηκαν!

## Οθόνες

### activity_main.xml
Εχει το ρόλο της αρχικής οθόνης και της οθόνης αναζήτησης.<br>
Αποτελείτε από:
- ένα RecyclerView ("@+id/rvrecipeItems") - για την προβολή τίτλων συνταγών 
- ένα EditText ("@+id/etSearchIng") - για την εισαγωγή όρου προς αναζήτηση
- ένα Button ("@+id/btnSearchRecipe") - ξεκινάει την αναζήτηση

### row_item.xml

Έχει το ρόλο της συμπλήρωσης του RecyclerView με τα αποτελέσματα της αναζήτησης.<br>
Αποτελείτε από:
- Ένα CardView, αντί για Constraint Layout
- Ένα LinearLayout ("@+id/linearLayout") - για να κρατάει τα TextViews
- Ένα TextView ("@+id/tvname") - για το όνομα της συνταγής
- Ένα TextView ("@+id/tvsummary") - για την περίληψη της συνταγής -- Εδώ γράφω δείτε περισσότερα


### view_recipe.xml

Έχει το ρόλο να δείχνει την επιλεγμένη συνταγή στον χρήστη.<br>
Αποτελείτε από:
- Ένα TextView ("@+id/tvRecipeName") - για να δείχνει το όνοματης συνταγής
- Ένα ScrollView ("@+id/scrollView") - για να χωρέσουμε όλη τη συνταγή σε μια σελίδα
- Ένα LinearLayout - επειδή το ScrollView χωράει μόνο ένα αντικείμενο -- κρατάει όλα τα TextViews υλικών και βημάτων της συνταγής
- Μερικά TextViews για να δημιουργήσω κενό ανάμεσα στην κορυφή και τα πρώτα υλικά -- τα υλικά πήγαιναν πάνω από την οθόνη οπότε σκέφτηκα αυτή τη μπακάλικη λύση 
- Μία σειρά από TextViews που δείχνουν τα υλικά και τα βήματα της κάθε συνταγής
- Ένα ακόμα LinearLayout για να αντιμετωπίσω το πρόβλημα που εμφανιζόταν με κάποιες συσκευές -- κοβόταν το κείμενο στην αριστερή άκρη
- Ένα κουμπί ("@+id/btnToSearch") που επιστρέφει πίσω στην αρχική οθόνη

## Plugins (build.gradle -- app)
- `id 'kotlin-android-extensions'`

## Dependencies (build.gradle -- app)
- Retrofit library - For API access
`    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
`
- Gson Converter - For Json to String convertion
`    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
`

## Manifest
- Internet Access
`  <uses-permission android:name="android.permission.INTERNET"/> `

## Scripts

#### app > java > com.example.cookit > network
- *ApiInterface.kt* - Kotlin Interface

Http GET Request @ BASEURL/recipe-search/{text}/<br>
`@GET("recipe-search/{text}/")`<br>
{text} defined here (dynamic url based on user input)<br>
`fun getRecipe(@Path("text") text:String): Call<List<Recipe>>`<br>
Επιστρέφει retrofit2.Call response (μία λίστα συνταγών) για να τη χρησιμοποιήσουμε στην αναζήτηση συνταγών.<br>
Το {text} είναι το υλικό που εισάγει ο χρήστης.<br>

---

Http GET Request @ BASEURL/recipe-detail/{id}/<br>
`@GET("recipe-detail/{id}/")`<br>
{id} defined here (dynamic url based on user input)<br>
`fun getDetails(@Path("id") id:Int) : Call<Recipe>`<br>
Επιστρέφει retrofit2.Call response (μία συνταγή) για να τη χρησιμοποιήσουμε στην προβολή της συνταγής <br>
Το {id} είναι το αναγνωριστικό νούμερο της κάθε συνταγής, αποθηκευμένο στη βάση δεδομένων.<br>

---

- *Recipe.kt* - Kotlin data class 
Data Class γιατί αποθηκεύει μόνο δεδομένα.<br>

Περιέχει όλα τα πεδία που υπάρχουν στη βάση δεδομένων του server<br>

`import com.google.gson.annotations.SerializedName`<br>
Χρησιμοποιούμε τη βιβλιοθήκη gson για να απευθυνθούμε σε αυτά τα πεδία.<br>

Σημείωση ότι όλα τα πεδία είναι arguments του constructor της κλάσης.<br>

#### app > java > com.example.cookit

- *RowAdapter.kt* - RecyclerView Adapter<br>
Κληρονομεί από την RecyclerView.Adapter<br>
`class RowAdapter(val context: Context, val recipeList: List<Recipe>) : RecyclerView.Adapter<RowAdapter.ViewHolder>()`<br>

Φτιάχνουμε ViewHolder class η οποία κληρονομεί την RecyclerView.ViewHolder.<br>

> you have to create ViewHolders to keep references in memory. [recycler view tutorial](https://www.raywenderlich.com/1560485-android-recyclerview-tutorial-with-kotlin)

Δημιουργούμε τα ακόλουθα στοιχεία που θέλουμε να δείξουμε στον RecyclerView:<br>
` var name: TextView`<br>
`var summary: TextView`<br>
`var layout: LinearLayout`<br>
και τους δίνουμε τιμή στον constructor<br>
`name = itemView.tvname`<br>
`summary = itemView.tvsummary`<br>
`layout = itemView.linearLayout`<br>

Τις απαιτεί ο Adapter<br>
`override fun onCreateViewHolder`<br>
! Θεωρώ αυτή τη μέθοδο "μαύρο κουτί". Απλά δουλεύει !<br>
> creates a view for the ViewHolder.[recycler view tutorial](https://www.raywenderlich.com/1560485-android-recyclerview-tutorial-with-kotlin)<br>

`override fun onBindViewHolder`<br>
Θέτουμε τα στοιχεία που δείχνουμε στο RecyclerView ίσα με τα στοιχεία της λίστας συνταγών στη συγκεκριμένη θέση position, δηλαδή στη συγκεκριμένη συνταγή, μέσω του viewholder αντικειμένου holder.<br>
` holder.name.text = recipeList[position].name`<br>
`holder.summary.text = recipeList[position].summary`<br>
Κάνουμε τα "παιδιά" του recyclerView να αντιδρούν στο κλικ<br>
`holder.layout.setOnClickListener`<br>
Δημιουργούμε intent και περνάμε reference του RecipeActivity που θα δούμε μετά.<br>

> It (intent) can be used with startActivity to launch an android.app.Activity [developer.android.com](https://developer.android.com/reference/kotlin/android/content/Intent)<br>
`val intent: Intent = Intent(context, RecipeActivity::class.java)`<br>

Η εφαρμογή κράσαρε χωρίς αυτό το flag<br>
`intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK`<br>

Δημιουργία retrofit και gson αντικειμένου<br>
`val BASE_URL = "https://sintagesapi.herokuapp.com/api/"`<br>
            `val retrofitBuilder = Retrofit.Builder()`<br>
                `.addConverterFactory(GsonConverterFactory.create())`<br>
                `.baseUrl(BASE_URL)`<br>
                `.build()`<br>
                `.create(ApiInterface::class.java)`<br>

Παίρνουμε της πληροφορίες με τη GET request χρησιμοποιόντας το recipeList[position].id ως id.<br>
Έτσι παίρνουμε τις πληροφορίες της συνταγής πουυ πάτησε ο χρήστης. <br>
`val retrofitData = retrofitBuilder.getDetails(recipeList[position].id)`<br>

Στη συνέχεια καλούμε τη μέθοδο enqueue του retrofit<br>
Αν είναι πετυχημένη (onResponse), τότε θέτουμε μεταβλητή `val responseBody = response.body()`, όπου response.body το σώμα της απάντησης στη GET request.<br>

Αν αυτή δεν είναι null, τότε ετοιμάζουμε να περάσουμε όλες τις πληροφορίες τις συνταγής στο επόμενο activity με την εντολή `intent.putExtra`.<br>

Αν είναι null τυπώνουμε μήνυμα λάθους στη κονσόλα για να μην κρασάρει το πρόγραμμα.<br>

Αν η μέθοδος enqueue δεν ήταν πετυχημένη για οποιοδήποτε λόγο (onFailure), τυπώνουμε μήνυμα λάθους στη κονσόλα.<br>

`override fun getItemCount`<br>
Τέλος επιστρέφουμε το μέγεθος της λίστας συνταγών στην getItemCount<br>

---

- *RecipeActivity.kt* - View recipe

`override fun onCreate`<br>
Θέτουμε view `setContentView(R.layout.view_recipe)`<br>

`btnToSearch.setOnClickListener(View.OnClickListener {`<br>
            `fun onClick(){`<br>
                `val i = Intent(this, MainActivity::class.java)`<br>
                `startActivity(i)`<br>
            `}`<br>
            `onClick()`<br>
OnClickListener για το κουμπί ("@+id/btnToSearch") ώστε να επιστρέφει στην αρχική οθόνη.<br>

`getIncomingIntent()`<br>
Τρέχει αυτή συνάρτηση.<br>

`private fun getIncomingIntent`<br>
Ελέγχει αν η το intent έχει τα απαραίτητα extras (στοιχεία συνταγής) και στη συνέχεια τα θέτει σε μεταβλητές. Στη συνέχεια περνάμε αυτές τις μεταβλητές στη συνάρτηση setRecipe.<br>

`private fun setRecipe`<br>
Βρίσκει όλα τα στοιχεία από το view_recipe.xml και τους δίνει την αντίστοιχη τιμή που περάσαμε στη συνάρτηση από τη getIncomingIntent. Αυτές είναι και οι τιμές που βλέπουμε στην οθόνη μας.<br>

---

- *MainActivity.kt*

` lateinit var rowAdapter: RowAdapter`<br>
    `lateinit var LinearLayoutManager: LinearLayoutManager`<br>
Θέτουμε μεταβλητές για την κλάση RowAdapter kai για τον linearLayoutManager<br>

`override fun onCreate`<br>
Θέτουμε view `setContentView(R.layout.activity_main)`<br>

`var btnSearch: Button`<br>
`var etSearch: EditText`<br>
`btnSearch = findViewById(R.id.btnSearchRecipe)`<br>
`etSearch = findViewById(R.id.etSearchIng)`<br>
Βρίσκουμε τα στοιχεία από το activity_main.xml<br>

`rvrecipeItems.setHasFixedSize(true)`<br>
        `LinearLayoutManager = LinearLayoutManager(this)`<br>
        `rvrecipeItems.layoutManager = LinearLayoutManager`<br>
Σετάρουμε το RecyclerView<br>

`btnSearch.setOnClickListener(View.OnClickListener() {`<br>
            `fun onClick() {`<br>
                `val text = etSearch.text.toString().toLowerCase().trim()`<br>
            `    Log.v("Button pressed",""+text)`<br>
            `    getRecipes(text)`<br>
            `}`<br>
            `onClick()`<br>
        `})`<br>
Σετάρουμε OnClickListener για το κουμπί ("@+id/btnSearchRecipe") και παίρνουμε το value που έβαλε ο χρήστης στο editText ("@+id/etSearchIng").<br>
Είναι σημαντικό πως όταν παίρνουμε το κείμενο από το editText ("@+id/etSearchIng") το μετατρέπουμε σε lowercase και κόβουμε τα κενά με τις μεθόδους toLowerCase() και trim(). Έτσι παίρνουμε περισσότερα αποτελέσματα στην αναζήτηση.<br>

`getRecipes(text)`<br>
Μόλις πατήσουμε το κουμπί καλούμε τη getRecipes με παράμετρο την είσοδο του χρήστη.<br>

` private fun getRecipes`<br>
Ακολουθεί ο ίδιος κώδικας με πριν για την κατασκευή retrofit και gson αντικειμένου. Σε αυτή την περίπτωση έχουμε στην onResponse<br>
`rowAdapter = RowAdapter(baseContext, responseBody)`<br>
                        `rowAdapter.notifyDataSetChanged()`<br>
                        `rvrecipeItems.adapter = rowAdapter`<br>
που ανανεώνει το recyclerView και βλέπουμε τα αποτελέσματα της αναζήτησης στην οθόνη μας.<br>

