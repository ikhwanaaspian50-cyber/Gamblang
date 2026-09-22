package com.example.data

import com.example.model.Article
import com.example.model.CategoryItem
import com.example.model.DailyReflection

object ArticleData {

    val dailyReflection = DailyReflection(
        id = "refl_sirrul_asrar_1",
        quote = "“Satu tarikan dari tarikan-tarikan Allah Al-Haq sebanding dengan ibadah seluruh jin dan manusia.”",
        author = "Syekh Abdul Qadir Al-Jailani",
        bookTitle = "Sirrul Asrar (Rahasia dari Segala Rahasia)",
        readTime = "5 mnt renungan",
        commentary = "Tarikan (Jadzbah) ilahiah adalah keterbukaan bashirah (mata hati) tatkala hijab-hijab keduniawian disingkapkan oleh Rahmat-Nya. Beramal tanpa kehadiran hati ibarat jasad tanpa nyawa; satu detik hati yang bergetar dalam kesadaran hakiki melampaui himpunan ibadah lahiriah yang hampa dari rasa kehadiran Ilahi."
    )

    val categories = listOf(
        CategoryItem(
            id = "all",
            name = "Semua",
            count = 6,
            description = "Seluruh naskah, kajian, dan catatan renungan",
            iconName = "auto_stories"
        ),
        CategoryItem(
            id = "tasawuf",
            name = "Tasawuf & Qolbu",
            count = 2,
            description = "Kajian kedalaman batin, penyucian jiwa, dan dzikir kalbu",
            iconName = "favorite"
        ),
        CategoryItem(
            id = "filologi",
            name = "Naskah Kuno",
            count = 1,
            description = "Kajian filologi manuskrip kuno Sunda dan Nusantara",
            iconName = "history_edu"
        ),
        CategoryItem(
            id = "kisah_nabi",
            name = "Kisah Nabi",
            count = 1,
            description = "Hikmah sirah dan wasilah kenabian terdahulu",
            iconName = "menu_book"
        ),
        CategoryItem(
            id = "tokoh_sufi",
            name = "Biografi Waliyullah",
            count = 1,
            description = "Manakib para auliya dan penyebar tarekat tatar Sunda",
            iconName = "person"
        ),
        CategoryItem(
            id = "amalan",
            name = "Fadhilah Amalan",
            count = 1,
            description = "Keutamaan surat-surat Al-Qur'an dan wirid penyejuk",
            iconName = "spa"
        ),
        CategoryItem(
            id = "hakikat",
            name = "Hakikat Jiwa",
            count = 1,
            description = "Ontologi marifatullah mengenai martabat ruhaniyah",
            iconName = "psychology"
        )
    )

    val articles = listOf(
        Article(
            id = "mengenal-getaran-qolbu",
            title = "Mengenal Getaran Qolbu",
            category = "Tasawuf",
            readTimeMinutes = 7,
            source = "Kajian Kitab Sirrul Asrar karya Syekh Abdul Qadir Al-Jailani",
            publishDate = "Kajian Tasawuf Gamblang",
            summary = "Kajian mendalam dari kitab Sirrul Asrar karya Syekh Abdul Qadir Al-Jailani: Membedah rahasia getaran hati jasmani dan getaran suci ruhani saat tersentuh kalimat Tauhid.",
            quranVerseArabic = "اَفَمَنْ شَرَحَ اللّٰهُ صَدْرَهٗ لِلْاِسْلَامِ فَهُوَ عَلٰى نُوْرٍ مِّنْ رَّبِّهٖ ۗفَوَيْلٌ لِّلْقٰسِيَةِ قُلُوْبُهُمْ مِّنْ ذِكْرِ اللّٰهِ ۗ اُولٰۤىِٕكَ فِيْ ضَلٰلٍ مُّبِيْنٍ",
            quranVerseTranslation = "“Maka, apakah orang yang Allah bukakan hatinya untuk menerima Islam, lalu mendapat cahaya dari Tuhannya sama dengan orang yang hatinya membatu dari mengingat Allah? Mereka itu dalam kesesatan yang nyata.”",
            quranVerseRef = "QS. Az-Zumar [39]: 22",
            contentParagraphs = listOf(
                "Di dalam khazanah tasawuf, hati (qolbu) bukanlah sekadar gumpalan daging fisik yang memompa darah ke seluruh pembuluh jasmani. Para arifin mengibaratkan qolbu jasmani hanyalah singgasana wadag, sedangkan hakikat qolbu adalah latifah rohaniah—titik tembus cahaya ilahiah di pusat mikrokosmos manusia.",
                "Dalam kitab agung Sirrul Asrar, Syekh Abdul Qadir Al-Jailani menjelaskan bahwa qolbu memiliki getaran alami. Getaran pertama adalah getaran thabi'i (biologis) yang bertaut dengan denyut nadi dan ritme tarikan nafas. Namun, saat kalimah thayyibah 'Laa ilaha illallah' dihunjamkan ke sanubari dengan penuh adab dan hudhur (kehadiran jiwa), getaran kedua akan bangkit: getaran rohani yang membakar karat-karat egoisme (an-nafs al-ammarah).",
                "Ketika getaran rohani ini selaras dengan zikir, dada manusia terasa lapang, sejuk, dan memancarkan ketenangan yang tak terkatakan. Itulah makna 'syarahallahu shadrahu lil-Islam'—Allah melapangkan dada seorang hamba sehingga ia senantiasa berjalan di bawah pendar cahaya Tuhannya.",
                "Sebaliknya, hati yang lalai dari zikir akan mengalami pembekuan rasa ('qaswah'). Getarannya terkunci dalam kubangan duniawi, menganggap remeh hikmah, dan mudah gelisah tatkala diuji. Melalui latihan zikir nafi itsbat dan merenungi setiap desah nafas, hamba diajak menyelaraskan detak jantung lahiriah dengan tasbih batiniah, hingga seluruh sel tubuh bersaksi dalam keheningan yang agung."
            ),
            tags = listOf("Tasawuf", "Sirrul Asrar", "Qolbu", "Dzikir", "Hakikat"),
            isFeatured = true
        ),
        Article(
            id = "martabat-tujuh-majalengka-1249",
            title = "Martabat Tujuh Majalengka Tahun 1249",
            category = "Filologi",
            readTimeMinutes = 10,
            source = "Balai Naskah Sri Baduga (Koleksi No. 07.07)",
            publishDate = "Koleksi Naskah Kuno",
            summary = "Kajian naskah kuno Sri Baduga nomor 07.07 tentang ajaran emanasi ketuhanan: Ahadiyah, Wahdah, Wahidiyah hingga Nur Muhammad dan konsep Insan Kamil.",
            quranVerseArabic = "كُنْتُ كَنْزًا مَخْفِيًّا فَأَحْبَبْتُ أَنْ أُعْرَفَ فَخَلَقْتُ الْخَلْقَ لِكَيْ أُعْرَفَ",
            quranVerseTranslation = "“Aku adalah perbendaharaan yang tersembunyi, lalu Aku ingin dikenal, maka Ku-ciptakan makhluk agar mereka mengenal-Ku.”",
            quranVerseRef = "Hadits Qudsi Masyhur",
            contentParagraphs = listOf(
                "Naskah beraksara Pegon berbahasa Jawa-Sunda kuno yang tersimpan di Museum Sri Baduga Bandung dengan kode koleksi 07.07 bertarikh 1249 Hijriyah (sekitar abad ke-19 Masehi) mencatat babak penting transmisi ajaran tasawuf falsafi di tatar Majalengka.",
                "Ajaran yang diuraikan bersumber dari doktrin Martabat Tujuh yang awalnya dirumuskan oleh Syekh Muhammad bin Fadhlullah Al-Burhanpuri dalam kitab At-Tuhfah Al-Mursalah ila Ruhin Nabi, yang kemudian disyarahkan oleh para ulama Nusantara seperti Syekh Syamsuddin As-Sumatrani dan Syekh Abdul Muhyi Pamijahan.",
                "Tujuh martabat manifestasi wujud tersebut terbagi menjadi tingkatan Qadim (Baqa) dan tingkatan Muhdats (ciptaan):\n1. Martabat Ahadiyah: Dzat Mutlak tanpa sifat dan tanpa asma (La Ta'ayyun).\n2. Martabat Wahdah: Hakikat Muhammadiyyah / Nur Muhammad (Ta'ayyun Awwal).\n3. Martabat Wahidiyah: Hakikat Insaniyyah tempat terbitnya asma dan sifat (Ta'ayyun Tsani).\n4. Alam Arwah: Penjelmaan alam ruh yang murni dan lembut.\n5. Alam Mitsal: Alam citra perumpamaan yang belum berwujud materi tebal.\n6. Alam Ajsam: Alam fisik kebendaan yang kasat mata.\n7. Alam Insan Kamil: Puncak perpaduan seluruh martabat dalam diri manusia paripurna.",
                "Kajian filologis ini membuktikan betapa mendalamnya penghayatan metafisika para leluhur di tanah Pasundan, di mana suluk spiritual tidak dipisahkan dari etika kepenulisan naskah berbahan kertas saeh dan tinta alami yang lestari berabad-abad."
            ),
            tags = listOf("Filologi", "Sri Baduga", "Martabat Tujuh", "Majalengka", "Manuskrip"),
            isFeatured = false
        ),
        Article(
            id = "kisah-nabi-syits-wasilah-awal",
            title = "Kisah Nabi Syits: Wasilah Kenabian Awal",
            category = "Kisah Nabi",
            readTimeMinutes = 6,
            source = "Kitab Qasas al-Anbiya & Tarikh ath-Thabari",
            publishDate = "Sirah Nabawiyyah",
            summary = "Anugerah penghibur duka Nabi Adam setelah wafatnya Habil. Wasiat tabut suci, suhuf pusaka, dan rahasia pertemuan Nabi Adam dan Hawa di padang Arafah.",
            quranVerseArabic = "وَوَهَبْنَا لَهُ مِنْ رَّحْمَتِنَآ",
            quranVerseTranslation = "“Dan Kami anugerahkan kepadanya sebagian dari rahmat Kami...”",
            quranVerseRef = "Tadabbur Sirah Nabawiyyah",
            contentParagraphs = listOf(
                "Setelah syahidnya Habil di tangan Qabil, duka mendalam menyelimuti Nabi Adam AS dan Sayyidah Hawa selama bertahun-tahun. Allah SWT yang Maha Pengasih kemudian menganugerahkan seorang putra suci yang diberi nama Syits, yang dalam bahasa Ibrani bermakna 'Hibatullah'—pemberian karunia pengganti dari Allah.",
                "Nabi Syits AS dianugerahi rupa yang paling menyerupai ayahnya, budi pekerti yang santun, serta kebijaksanaan batin yang luar biasa. Kepadanyalah Nabi Adam AS mewariskan rahasia makrifat, penjagaan waktu-waktu ibadah, serta tabut pusaka yang memuat catatan suhuf kenabian.",
                "Sebanyak 50 suhuf diturunkan oleh Allah SWT kepada Nabi Syits AS yang berisi hukum-hukum tata krama, syariat tauhid awal, serta peringatan agar keturunan beriman tidak bercampur dengan keturunan durhaka yang mengikuti hawa nafsu.",
                "Kisah Nabi Syits adalah cermin keteguhan menjaga pelita nur kenabian di tengah generasi awal manusia. Dari sulbinya, cahaya terpilih diteruskan hingga Nabi Idris, Nabi Nuh, sampai bermuara pada junjungan kita Nabi Muhammad SAW."
            ),
            tags = listOf("Kisah Nabi", "Nabi Syits", "Sirah", "Adam", "Hikmah"),
            isFeatured = false
        ),
        Article(
            id = "syekh-haji-abdul-muhyi-pamijahan",
            title = "Syekh Haji Abdul Muhyi (Pamijahan Tasikmalaya)",
            category = "Tokoh Sufi",
            readTimeMinutes = 8,
            source = "Manakib Waliyullah Tatar Sunda",
            publishDate = "Biografi Auliya",
            summary = "Ulama pembawa Tarekat Syathariyah ke tatar Sunda. Perjalanan uzlah di Gua Pamijahan, riwayat berguru ke Syekh Abdul Rauf Singkel, hingga ziarah ke Bagdad.",
            quranVerseArabic = "اَلَآ اِنَّ اَوْلِيَاۤءَ اللّٰهِ لَا خَوْفٌ عَلَيْهِمْ وَلَا هُمْ يَحْزَنُوْنَ",
            quranVerseTranslation = "“Ingatlah, sesungguhnya wali-wali Allah itu, tidak ada kekhawatiran terhadap mereka dan tidak (pula) mereka bersedih hati.”",
            quranVerseRef = "QS. Yunus [10]: 62",
            contentParagraphs = listOf(
                "Syekh Haji Abdul Muhyi lahir di Mataram sekitar tahun 1650 M dari pasangan Raden Tumenggung Wirachandra dan Raden Ajeng Tanganiah. Garis nasabnya bersambung hingga Sunan Giri (Raden Paku) di Gresik.",
                "Setelah menimba ilmu dasar agama di Ampel Denta Surabaya, beliau berlayar ke Kesultanan Aceh Darussalam untuk berguru selama belasan tahun kepada ulama sufi terkemuka Syekh Abdul Rauf as-Singkili (Syiah Kuala). Dari gurunya inilah beliau dibaiat dan diberi izin menyebarkan Tarekat Syathariyah.",
                "Ketika menunaikan ibadah haji ke Makkah dan berziarah ke makam Syekh Abdul Qadir Al-Jailani di Baghdad, beliau menerima isyarat batin dari sang waliyullah untuk mencari sebuah gua di selatan Jawa bagian barat yang memiliki ciri tanah dan tetesan air khusus untuk berkhalwat.",
                "Setelah perjalanan panjang melintasi Cirebon, Darma Kuningan, dan Pameungpeuk Garut, sampailah beliau di lembah Pamijahan Tasikmalaya. Di Gua Safarwadi itulah beliau berkhalwat, mengajarkan dzikir jahar dan sirr, mendidik para santri, dan meletakkan fondasi peradaban Islam tasawuf yang teduh di bumi Parahyangan."
            ),
            tags = listOf("Waliyullah", "Pamijahan", "Syathariyah", "Tasikmalaya", "Biografi"),
            isFeatured = false
        ),
        Article(
            id = "15-keutamaan-surat-al-kautsar",
            title = "15 Keutamaan Surat Al-Kautsar",
            category = "Amalan & Doa",
            readTimeMinutes = 5,
            source = "Khazanah Asrar Al-Quran & Tafsir Al-Baghawi",
            publishDate = "Amalan Harian",
            summary = "Surat terpendek sebagai penenang hati Rasulullah SAW. Fadhilah pelunak kalbu, pembuka pintu rezeki, penghapus ketakutan, dan wasilah air surga.",
            quranVerseArabic = "اِنَّآ اَعْطَيْنٰكَ الْكَوْثَرَۗ فَصَلِّ لِرَبِّكَ وَانْحَرْۗ اِنَّ شَانِئَكَ هُوَ الْاَبْتَرُ",
            quranVerseTranslation = "“Sungguh, Kami telah memberimu nikmat yang banyak. Maka laksanakanlah salat karena Tuhanmu, dan berkurbanlah. Sungguh, orang-orang yang membencimu dialah yang terputus.”",
            quranVerseRef = "QS. Al-Kautsar [108]: 1-3",
            contentParagraphs = listOf(
                "Surat Al-Kautsar terdiri dari tiga ayat yang ringkas, namun sarat dengan limpahan mukjizat dan rahasia penghiburan ilahi. Surat ini diturunkan tatkala kaum musyrikin Quraisy mencemooh Rasulullah SAW dengan sebutan 'Abtar' (terputus nasabnya) setelah wafatnya putra beliau, Ibrahim dan Qasim.",
                "Allah SWT menjawab celaan tersebut dengan menjanjikan Al-Kautsar: telaga kemuliaan di surga yang airnya lebih putih dari susu, lebih manis dari madu, serta kebaikan tak terhingga di dunia dan akhirat.",
                "Di antara 15 fadhilah dan keutamaan mengamalkan surat Al-Kautsar yang sering diwasiatkan para guru arifin adalah:\n• Menumbuhkan rasa syukur dan mengikis sifat kikir.\n• Menjadi wasilah penenteram hati dari bisikan waswas dan ketakutan.\n• Melunakkan hati keluarga dan sanak kerabat yang keras kepala.\n• Membuka pintu rezeki yang berkah lagi tak terduga.\n• Peneguh tekad dalam menegakkan salat sunnah dan sedekah kurban.\n• Wasilah memperoleh syafaat dan tegukan air sejuk telaga Kautsar di hari kiamat kelak.",
                "Membaca surat ini dengan tafakkur menghidupkan keyakinan bahwa kehilangan apa pun di dunia ini akan digantikan oleh Allah dengan karunia yang jauh lebih agung bagi hamba yang sabar."
            ),
            tags = listOf("Al-Quran", "Al-Kautsar", "Fadhilah", "Doa", "Tafsir"),
            isFeatured = false
        ),
        Article(
            id = "nama-nama-roh-dan-tingkatannya",
            title = "Nama-nama Roh dan Tingkatannya",
            category = "Hakikat Jiwa",
            readTimeMinutes = 6,
            source = "Kajian Marifatullah & Naskah Tasawuf Nusantara",
            publishDate = "Ontologi Jiwa",
            summary = "Menelusuri esensi batin manusia: dari Roh Nabati, Roh Insani, Roh Nurani, hingga Roh Illahiyah di bawah naungan Ruhul Qutub dalam mengenal diri sejati.",
            quranVerseArabic = "وَيَسْـَٔلُوْنَكَ عَنِ الرُّوْحِۗ قُلِ الرُّوْحُ مِنْ اَمْرِ رَبِّيْ وَمَآ اُوْتِيْتُمْ مِّنَ الْعِلْمِ اِلَّا قَلِيْلًا",
            quranVerseTranslation = "“Dan mereka bertanya kepadamu tentang roh. Katakanlah, 'Roh itu termasuk urusan Tuhanku, sedangkan kamu tidak diberi pengetahuan melainkan sedikit.'”",
            quranVerseRef = "QS. Al-Isra [17]: 85",
            contentParagraphs = listOf(
                "Dalam tradisi makrifatullah nusantara, kajian tentang roh bukan dimaksudkan untuk menentang rahasia ghaib, melainkan sebagai tangga bertahap ('taraqqi') bagi seorang salik agar mengenali jati dirinya: 'Man 'arafa nafsahu faqad 'arafa Rabbahu' (Barangsiapa mengenal dirinya, niscaya ia mengenal Tuhannya).",
                "Para guru sufi mengklasifikasikan tingkatan daya ruhaniyah manusia ke dalam beberapa maqam:\n1. Roh Jasmani: Menghidupkan organ fisik, memicu gerak otot dan indera kasar.\n2. Roh Nabati: Menumbuhkan sel, mengatur metabolisme dan sirkulasi nutrisi alami.\n3. Roh Hewani: Berpusat pada syahwat, emosi, amarah, dan insting mempertahankan diri.\n4. Roh Nafsani: Daya akal dan pertimbangan logika rasional.\n5. Rohani: Lapisan kalbu yang mulai merindukan kebaikan dan ibadah.\n6. Roh Rahmani: Terbuka pada kasih sayang universal dan keikhlasan berkorban.\n7. Roh Nurani: Cahaya fitrah yang mampu menangkap ilham dan rahasia asma Allah.\n8. Roh Qudus / Idlafi: Roh suci titipan langsung dari hembusan tiupan ilahi yang kekal dalam penyaksian tauhid hakiki.",
                "Mengenal tingkatan roh ini menyadarkan kita bahwa tujuan hidup bukanlah memanjakan roh hewani, melainkan menyucikan wadah diri agar Roh Nurani memimpin setiap gerak langkah menuju ridha Sang Khaliq."
            ),
            tags = listOf("Hakikat", "Ruhani", "Marifatullah", "Jiwa", "Tasawuf"),
            isFeatured = false
        )
    )

    val authorProfile = AuthorInfo(
        name = "Kang Iwe",
        title = "Kurator & Penulis Gamblang",
        motto = "“Hanya catatan sedikit berarti untuk direnungkan dan dilaksanakan.”",
        blogUrl = "https://234byte.blogspot.com/",
        sinceYear = "2009",
        articlesCount = 142,
        bio = "Penggiat kajian naskah kuno nusantara, suluk tasawuf, dan refleksi hikmah batiniah. Menulis di blog Gamblang sejak tahun 2009 sebagai ikhtiar menyajikan catatan ringkas yang jernih, tenang, dan berbobot bagi pejalan spiritual."
    )
}

data class AuthorInfo(
    val name: String,
    val title: String,
    val motto: String,
    val blogUrl: String,
    val sinceYear: String,
    val articlesCount: Int,
    val bio: String
)
