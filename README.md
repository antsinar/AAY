# Documentation Για Android Studio Project

[cookEat APK](https://github.com/antsinar/AAY/releases/tag/pre-release)

#### Για την υλοποίηση χρειάστηκαν οι εξής πηγές από το internet
- [Documentation Retrofit](https://square.github.io/retrofit/), γραμμένη δυστυχώς για Java
- [Official Android Developer](https://developer.android.com)
- [Retrofit Tutorial στο Youtube](https://www.youtube.com/watch?v=5gFrXGbQsc8)
- [Retrofit to RecyclerView Tutorial στο Youtube](https://www.youtube.com/watch?v=_bVWsL5CHh4)
- [RecyclerView Tutorial](https://www.raywenderlich.com/1560485-android-recyclerview-tutorial-with-kotlin)
- [Intent tutorial στο Youtube](https://www.youtube.com/watch?v=wKFJsrdiGS8)
- Stack Overflow, αν και οι περισσότερες λύσεις απευθύνονταν σε Java  
- Και η βοήθεια του κ.Καρασούλα

> Τα αρχεία και τα κομμάτια κώδικα που δεν αναφέρονται εν τέλει δεν χρησιμοποιήθηκαν!

## Οθόνες

### activity_main.xml
Εχει το ρόλο της αρχικής οθόνης και της οθόνης αναζήτησης.
Αποτελείτε από:
- ένα RecyclerView ("@+id/rvrecipeItems") - για την προβολή τίτλων συνταγών 
- ένα EditText ("@+id/etSearchIng") - για την εισαγωγή όρου προς αναζήτηση
- ένα Button ("@+id/btnSearchRecipe") - ξεκινάει την αναζήτηση

### row_item.xml

Έχει το ρόλο της συμπλήρωσης του RecyclerView με τα αποτελέσματα της αναζήτησης.
Αποτελείτε από:
- Ένα CardView, αντί για Constraint Layout
- Ένα LinearLayout ("@+id/linearLayout") - για να κρατάει τα TextViews
- Ένα TextView ("@+id/tvname") - για το όνομα της συνταγής
- Ένα TextView ("@+id/tvsummary") - για την περίληψη της συνταγής -- Εδώ γράφω δείτε περισσότερα


### view_recipe.xml

Έχει το ρόλο να δείχνει την επιλεγμένη συνταγή στον χρήστη.
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

Http GET Request @ BASEURL/recipe-search/{text}/
`@GET("recipe-search/{text}/")`
{text} defined here (dynamic url based on user input)
`fun getRecipe(@Path("text") text:String): Call<List<Recipe>>`
Επιστρέφει retrofit2.Call response (μία λίστα συνταγών) για να τη χρησιμοποιήσουμε στην αναζήτηση συνταγών.
Το {text} είναι το υλικό που εισάγει ο χρήστης.

---

Http GET Request @ BASEURL/recipe-detail/{id}/
`@GET("recipe-detail/{id}/")`
{id} defined here (dynamic url based on user input)
`fun getDetails(@Path("id") id:Int) : Call<Recipe>`
Επιστρέφει retrofit2.Call response (μία συνταγή) για να τη χρησιμοποιήσουμε στην προβολή της συνταγής 
Το {id} είναι το αναγνωριστικό νούμερο της κάθε συνταγής, αποθηκευμένο στη βάση δεδομένων.

---

- *Recipe.kt* - Kotlin data class 
Data Class γιατί αποθηκεύει μόνο δεδομένα.

Περιέχει όλα τα πεδία που υπάρχουν στη βάση δεδομένων του server

`import com.google.gson.annotations.SerializedName`
Χρησιμοποιούμε τη βιβλιοθήκη gson για να απευθυνθούμε σε αυτά τα πεδία.

Σημείωση ότι όλα τα πεδία είναι arguments του constructor της κλάσης.

#### app > java > com.example.cookit

- *RowAdapter.kt* - RecyclerView Adapter
Κληρονομεί από την RecyclerView.Adapter
`class RowAdapter(val context: Context, val recipeList: List<Recipe>) : RecyclerView.Adapter<RowAdapter.ViewHolder>()`

Φτιάχνουμε ViewHolder class η οποία κληρονομεί την RecyclerView.ViewHolder.

> you have to create ViewHolders to keep references in memory. [recycler view tutorial](https://www.raywenderlich.com/1560485-android-recyclerview-tutorial-with-kotlin)

Δημιουργούμε τα ακόλουθα στοιχεία που θέλουμε να δείξουμε στον RecyclerView:
` var name: TextView`
`var summary: TextView`
`var layout: LinearLayout`
και τους δείνουμε τιμή στον constructor
`name = itemView.tvname`
`summary = itemView.tvsummary`
`layout = itemView.linearLayout`

Τις απαιτεί ο Adapter
`override fun onCreateViewHolder`
! Θεωρώ αυτή τη μέθοδο "μαύρο κουτί". Απλά δουλεύει !
> creates a view for the ViewHolder.[recycler view tutorial](https://www.raywenderlich.com/1560485-android-recyclerview-tutorial-with-kotlin)

`override fun onBindViewHolder`
Θέτουμε τα στοιχεία που δείχνουμε στο RecyclerView ίσα με τα στοιχεία της λίστας συνταγών στη συγκεκριμένη θέση position, δηλαδή στη συγκεκριμένη συνταγή, μέσω του viewholder αντικειμένου holder.   
` holder.name.text = recipeList[position].name`
`holder.summary.text = recipeList[position].summary`
Κάνουμε τα "παιδιά" του recyclerView να αντιδρούν στο κλικ
`holder.layout.setOnClickListener`
Δημιουργούμε intent και περνάμε reference του RecipeActivity που θα δούμε μετά.

> It (intent) can be used with startActivity to launch an android.app.Activity [developer.android.com](https://developer.android.com/reference/kotlin/android/content/Intent)
`val intent: Intent = Intent(context, RecipeActivity::class.java)`

Η εφαρμογή κράσαρε χωρίς αυτό το flag
`intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK`

Δημιουργία retrofit και gson αντικειμένου
`val BASE_URL = "https://sintagesapi.herokuapp.com/api/"`
            `val retrofitBuilder = Retrofit.Builder()`
                `.addConverterFactory(GsonConverterFactory.create())`
                `.baseUrl(BASE_URL)`
                `.build()`
                `.create(ApiInterface::class.java)`

Παίρνουμε της πληροφορίες με τη GET request χρησιμοποιόντας το recipeList[position].id ως id.
Έτσι παίρνουμε τις πληροφορίες της συνταγής πουυ πάτησε ο χρήστης. 
`val retrofitData = retrofitBuilder.getDetails(recipeList[position].id)`

Στη συνέχεια καλούμε τη μέθοδο enqueue του retrofit
Αν είναι πετυχημένη (onResponse), τότε θέτουμε μεταβλητή `val responseBody = response.body()`, όπου response.body το σώμα της απάντησης στη GET request.

Αν αυτή δεν είναι null, τότε ετοιμάζουμε να περάσουμε όλες τις πληροφορίες τις συνταγής στο επόμενο activity με την εντολή `intent.putExtra`.

Αν είναι null τυπώνουμε μήνυμα λάθους στη κονσόλα για να μην κρασάρει το πρόγραμμα.

Αν η μέθοδος enqueue δεν ήταν πετυχημένη για οποιοδήποτε λόγο (onFailure), τυπώνουμε μήνυμα λάθους στη κονσόλα.

`override fun getItemCount`
Τέλος επιστρέφουμε το μέγεθος της λίστας συνταγών στην getItemCount

---

- *RecipeActivity.kt* - View recipe

`override fun onCreate`
Θέτουμε view `setContentView(R.layout.view_recipe)`

`btnToSearch.setOnClickListener(View.OnClickListener {`
            `fun onClick(){`
                `val i = Intent(this, MainActivity::class.java)`
                `startActivity(i)`
            `}`
            `onClick()`
OnClickListener για το κουμπί ("@+id/btnToSearch") ώστε να επιστρέφει στην αρχική οθόνη.

`getIncomingIntent()`
Τρέχει αυτή συνάρτηση.

`private fun getIncomingIntent`
Ελέγχει αν η το intent έχει τα απαραίτητα extras (στοιχεία συνταγής) και στη συνέχεια τα θέτει σε μεταβλητές. Στη συνέχεια περνάμε αυτές τις μεταβλητές στη συνάρτηση setRecipe.

`private fun setRecipe`
Βρίσκει όλα τα στοιχεία από το view_recipe.xml και τους δίνει την αντίστοιχη τιμή που περάσαμε στη συνάρτηση από τη getIncomingIntent. Αυτές είναι και οι τιμές που βλέπουμε στην οθόνη μας.

---

- *MainActivity.kt*

` lateinit var rowAdapter: RowAdapter`
    `lateinit var LinearLayoutManager: LinearLayoutManager`
Θέτουμε μεταβλητές για την κλάση RowAdapter kai για τον linearLayoutManager

`override fun onCreate`
Θέτουμε view `setContentView(R.layout.activity_main)`

`var btnSearch: Button`
`var etSearch: EditText`
`btnSearch = findViewById(R.id.btnSearchRecipe)`
`etSearch = findViewById(R.id.etSearchIng)`
Βρίσκουμε τα στοιχεία από το activity_main.xml

`rvrecipeItems.setHasFixedSize(true)`
        `LinearLayoutManager = LinearLayoutManager(this)`
        `rvrecipeItems.layoutManager = LinearLayoutManager`
Σετάρουμε το RecyclerView

`btnSearch.setOnClickListener(View.OnClickListener() {`
            `fun onClick() {`
                `val text = etSearch.text.toString().toLowerCase().trim()`
            `    Log.v("Button pressed",""+text)`
            `    getRecipes(text)`
            `}`
            `onClick()`
        `})`
Σετάρουμε OnClickListener για το κουμπί ("@+id/btnSearchRecipe") και παίρνουμε το value που έβαλε ο χρήστης στο editText ("@+id/etSearchIng").
Είναι σημαντικό πως όταν παίρνουμε το κείμενο από το editText ("@+id/etSearchIng") το μετατρέπουμε σε lowercase και κόβουμε τα κενά με τις μεθόδους toLowerCase() και trim(). Έτσι παίρνουμε περισσότερα αποτελέσματα στην αναζήτηση.

`getRecipes(text)`
Μόλις πατήσουμε το κουμπί καλούμε τη getRecipes με παράμετρο την είσοδο του χρήστη.

` private fun getRecipes`
Ακολουθεί ο ίδιος κώδικας με πριν για την κατασκευή retrofit και gson αντικειμένου. Σε αυτή την περίπτωση έχουμε στην onResponse
`rowAdapter = RowAdapter(baseContext, responseBody)`
                        `rowAdapter.notifyDataSetChanged()`
                        `rvrecipeItems.adapter = rowAdapter`
που ανανεώνει το recyclerView και βλέπουμε τα αποτελέσματα της αναζήτησης στην οθόνη μας.