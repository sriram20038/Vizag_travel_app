package com.example.travel

import android.graphics.Canvas
import android.location.Address
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.os.Handler
import android.widget.Button
import android.widget.LinearLayout
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.graphics.Paint
import android.os.Build
import android.view.View
import android.graphics.Color
import android.graphics.text.LineBreaker
import android.support.annotation.RequiresApi
import android.text.Html
import android.text.Spannable
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.LineBackgroundSpan


class MainActivity : AppCompatActivity() {
    var curretImg = 0
    lateinit var image: ImageView
    lateinit var text: TextView
    val colors = mapOf(
        0 to "#FFCDD2", // Light Red
        1 to "#F8BBD0", // Light Pink
        2 to "#E1BEE7", // Lavender Purple
        3 to "#D1C4E9", // Light Purple
        4 to "#BBDEFB", // Light Blue
        5 to "#B3E5FC", // Sky Blue
        6 to "#B2EBF2", // Aqua Blue
        7 to "#C8E6C9", // Light Green
        8 to "#DCEDC8"  // Lime Green
    )



    val places = mapOf(
        0 to "Kailasagiri",
        1 to "Rishikonda Beach",
        2 to "RK Beach",
        3 to "INS Kursura Submarine",
        4 to "Varaha Lakshmi Narasimha Temple",
        5 to "Katiki Waterfalls",
        6 to "Yarada Beach",
        7 to "Indira Gandhi Zoological Park",
        8 to "GVPCE"
    )

    val overviews = mapOf(
        0 to "At a height of 173 metres, the main attractions at Kailasagiri Park are " +
                "12-metre tall statues of Lord Shiva and goddess Parvathi, a floral clock " +
                "and the Titanic viewpoint.",
        1 to "Also called the ‘Jewel of the East Coast’, this pristine beach is surrounded" +
                " by emerald-green rocky hills and will captivate you with its sparkling golden shores and " +
                "swaying palm trees.",
        2 to "RK Beach, also known as Ramakrishna Beach, is a popular beach in Visakhapatnam, Andhra Pradesh, located on the east" +
                " coast of the Bay of Bengal. It is known for its golden sands, rhythmic waves, and natural beauty." +
                " The beach is named after the Ramakrishna Mission ashram nearby, and is surrounded by tourist attractions",
        3 to "A diesel electric submarine, INS Kursura Submarine was in commission for 31 years including during the Indo-Pakistan" +
                " War of 1971 when it was used to conduct patrol missions.",
        4 to "Also known as Simhachalam Temple, this 11th century temple sits on the Simhachalam Hill, about 300 metres above the sea level and is dedicated to the incarnation of Lord Vishnu.",
        5 to "Located near Borra Caves amidst lush green forests of the Eastern Ghats, Katiki Waterfalls have a drop of over 15 metres, and originate from the Gosthani River.",
        6 to "Flanked by banana and coconut plantations against the backdrop of rocky hills, Yarada Beach is just the place to be if you want to seek solace from the fast-paced city life.",
        7 to "A 625-acre habitat for over 80 species of birdlife and wildlife, Indira Gandhi Zoological Park is home to sloth bears, tigers, giraffes, rhesus monkeys and mandrills.",
        8 to "Gayatri Vidya Parishad College of Engineering (GVPCE) is a self-financing college in Visakhapatnam, Andhra Pradesh. It offers 7 undergraduate courses and 13 postgraduate courses, and is affiliated with Jawaharlal Nehru Technological University (JNTU) Kakinada. GVPCE is known for its B.E., B.Tech, MCA, M.Sc, M.Com, M.E., and M.Tech courses."
    )




    val whatToExpect = mapOf(
        0 to "Spanning 380 acres with views of the Bay of Bengal and the city, Kailasagiri Park also " +
                "has a ropeway, toy train and facilities for paragliding and horse riding.",
        1 to "Along with being a great swimming beach, there are several water sports offered at" +
                " Rishikonda Beach like jet skiing, kayaking, snorkelling, parasailing, windsurfing and sailing.",
        2 to "The beach is known for its endless coastline, morning joggers, and multiple food options. In the evening, people enjoy swimming, sunbathing, surfing, playing beach volleyball, and camel rides. You can also take a ride in a traditional fishing boat or a local cruise to enjoy the sunset view",
        3 to "Before being converted to a museum ship, INS Kursura Submarine weighed 1,919 long tonnes when surfaced at a length of 91.3 metres with 10 torpedo tubes.",
        4 to "Seek blessings of Lord Narasimha, admire the intricate carvings and reliefs depicting the incarnations of Lord Vishnu, and soak in the panoramic city vistas from atop the hill.",
        5 to "There are no stairs or paved pathways to Katiki Waterfalls and visitors need to trek through the forest or hire a local jeep service to cross a muddy trail.",
        6 to "Soak in breathtaking sea vistas, picnic with loved ones, make sand castles, enjoy soothing walks, interact with locals and gorge on delicious seaside delicacies served at the nearby stalls.",
        7 to "A pollution-free zone, Indira Gandhi Zoological Park has a learning centre with exhibits, open-air serpentarium and has battery operated vehicles to transport visitors between attractions.",
        8 to "The college offers instruction to 1200[1] undergraduate students in seven branches of engineering: chemical,[2] civil,[3] computer science and engineering,[4] electronics and communication engineering,[5] electrical engineering,[6] mechanical engineering[7] and information technology.[8] The institution also offers Master of Computer Applications program affiliated to Jawaharlal Nehru Technological University, Kakinada. All the under graduation programs are accredited by NBA in 2006["
    )

    val tips = mapOf(
        0 to "There is an entrance fee for the park.\n" +
                "The park is open from 6:00 am to 7:30 pm.\n" +
                "There is a café inside the park.",
        1 to "There is no entrance fee for the beach.\n" +
                "Water sports at the beach are available on a chargeable basis.\n" +
                "The beach is best visited during daylight hours.",
        2 to "Visiting the aquarium\n" +
                "Visiting the lighthouse\n" +
                "Visiting the Kali Temple\n" +
                "Taking a camel ride\n" +
                "Taking a boat ride in a fishing boat",
        3 to "There is an entrance fee for the museum.\n" +
                "The museum is closed on Mondays.\n" +
                "The museum is open from 2:00 p.m. to 8:30 p.m.",
        4 to "Plan your trip around Akshay Tritiya to witness the grand celebrations at the temple.\n" +
                "The timings of the temple are from 7:00 a.m. to 4:00 p.m. and from 6:00 p.m. to 9:00 p.m.",
        5 to "The waterfall is not recommended for elderly people.\n" +
                "The waterfall is accessible from 6:00 a.m. to 7:00 p.m.\n" +
                "Wear a comfortable pair of shoes when visiting the waterfall.",
        6 to "Do visit Dolphin's Nose (about 15 minutes from Yarada Beach) which houses an old lighthouse and offers gorgeous views of the city.\n" +
                "Gangavaram Beach is another tourist spot that you can visit when here.\n" +
                "Carry water and light snacks along if you want to enjoy a mini-picnic at the beach.",
        7 to "There is an entrance fee for the zoo.\n" +
                "The zoo is open from 9:00 a.m. to 5:00 p.m.\n" +
                "The zoo is closed on Mondays.",
        8 to "The campus is located in Madhurawada, on the outskirts of the city of Visakhapatnam. The institute is well accessible via NH16 with the nearest railway station and airport situated in Visakhapatnam. The resources in the campus include a post office, student service center, a stationery shop, canteen and banking facilities."
    )

    val addresses = mapOf(
        0 to "Hill Top Road, Kailasagiri, Visakhapatnam, Andhra Pradesh, 530043, India",
        1 to "Beach Rd, Rushikonda, Bheemili, Visakhapatnam, Andhra Pradesh, 530045, India",
        2 to "Beach Road, Visakhapatnam, Andhra Pradesh, 530003, India",
        3 to "R. K. Beach Road, Kirlampudi Layout, Chinna Waltair, Paanduranga Puram, Visakhapatnam, Andhra Pradesh, 530017, India",
        4 to "Simhachalam Hill, Near Gopalpattam Police Station, Visakhapatnam, Andhra Pradesh, 530028, India",
        5 to "Katiki Waterfalls Rd, Visakhapatnam, Andhra Pradesh, 531149, India.",
        6 to "Yarada, Visakhapatnam - 530005, Andhra Pradesh, India",
        7 to "Near Dairy Farm, Yendada Nursery, Visakhapatnam, Andhra Pradesh, 530040, India",
        8 to "Kommadi, Madhurawada, Visakhapatnam-530048, Andhra Pradesh, India"
    )

    val locations = mapOf(
        0 to "https://maps.app.goo.gl/YZfPFJfENRgCufhS7",
        1 to "https://maps.app.goo.gl/wyRiq67XfRvvGJyd6",
        2 to "https://maps.app.goo.gl/fJKFjgBayKRPK3qu5",
        3 to "https://maps.app.goo.gl/TSK3V9sBF5FKQxcJ8",
        4 to "https://maps.app.goo.gl/79sbCjYMxpJqcMcZ6",
        5 to "https://maps.app.goo.gl/P2PdBFgSq1QouVTw9",
        6 to "https://maps.app.goo.gl/xKt3SADMDNXEv1wKA",
        7 to "https://maps.app.goo.gl/41E5VmwqjHYcGNsq8",
        8 to "https://maps.app.goo.gl/gHMc6yMT4beMqVDSA"
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val scroleview = findViewById<ScrollView>(R.id.scroll)
        val prev = findViewById<ImageView>(R.id.prevbutton)
        val next = findViewById<ImageView>(R.id.nextbutton)
        val placename = findViewById<TextView>(R.id.text0)
        val overview = findViewById<TextView>(R.id.overviewcon)
        val WTE = findViewById<TextView>(R.id.whattoexpect)
        val Tipid = findViewById<TextView>(R.id.Tips)
        val addr = findViewById<TextView>(R.id.Address)
        val loc = findViewById<TextView>(R.id.Location)

        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView5 = findViewById<TextView>(R.id.textView5)
        val textView3 = findViewById<TextView>(R.id.textView3)
        val textView4 = findViewById<TextView>(R.id.textView4)
        val textView6 = findViewById<TextView>(R.id.textView6)
        val infoTextView = findViewById<TextView>(R.id.info)

        underlineTextView(textView2)
        underlineTextView(textView5)
        underlineTextView(textView3)
        underlineTextView(textView4)
        underlineTextView(textView6)
        underlineTextView(infoTextView)
        underlineTextView(Tipid)


        val overviewText = overviews[curretImg] ?: ""
        val spannableString = Html.fromHtml(overviewText)
        overview.text = spannableString


// Inside your onCreate method

        next.setOnClickListener {
            var idCurrentImageString = "pic$curretImg"
            var idCurrentImageInt =
                this.resources.getIdentifier(idCurrentImageString, "id", packageName)
            image = findViewById(idCurrentImageInt)
            image.alpha = 0f

            curretImg = (9 + curretImg + 1) % 9

            var idImageToShowString = "pic$curretImg"
            var idImageToShowInt =
                this.resources.getIdentifier(idImageToShowString, "id", packageName)
            image = findViewById(idImageToShowInt)
            image.alpha = 1f

            overview.text = overviews[curretImg]
            // Add justification mode here
            overview.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            // Rest of your existing code

            placename.text = places[curretImg]
            overview.text = overviews[curretImg]
            WTE.text = whatToExpect[curretImg]
            Tipid.text = tips[curretImg]
            addr.text = addresses[curretImg]
            loc.text = locations[curretImg]




            // Delay the scroll operation slightly
            Handler().postDelayed({
                scroleview.smoothScrollTo(0, 0)
            }, 100)
            scroleview.setBackgroundColor(Color.parseColor(colors[curretImg]))






        }

        prev.setOnClickListener {
            var idCurrentImageString = "pic$curretImg"
            var idCurrentImageInt =
                this.resources.getIdentifier(idCurrentImageString, "id", packageName)
            image = findViewById(idCurrentImageInt)
            image.alpha = 0f

            curretImg = (9 + curretImg - 1) % 9

            var idImageToShowString = "pic$curretImg"
            var idImageToShowInt =
                this.resources.getIdentifier(idImageToShowString, "id", packageName)
            image = findViewById(idImageToShowInt)
            image.alpha = 1f
            overview.text = overviews[curretImg]
            // Add justification mode here
            overview.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            // Rest of your existing code

            placename.text = places[curretImg]
            overview.text = overviews[curretImg]
            WTE.text = whatToExpect[curretImg]
            Tipid.text = tips[curretImg]
            addr.text = addresses[curretImg]
            loc.text = locations[curretImg]

            // Delay the scroll operation slightly
            Handler().postDelayed({
                scroleview.smoothScrollTo(0, 0)
            }, 100)
            scroleview.setBackgroundColor(Color.parseColor(colors[curretImg]))
        }




    }



    private fun underlineTextView(textView: TextView) {
        textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

}