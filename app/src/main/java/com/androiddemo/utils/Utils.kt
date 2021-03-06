package com.androiddemo.utils

import android.content.Context
import android.net.ConnectivityManager
import com.androiddemo.model.Movies
import java.util.regex.Pattern

public class Utils {

    companion object {

        fun isNetworkAvailable(context: Context): Boolean
        {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
        }

        fun isEmailValid(email: String): Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }

        fun getMovies() : ArrayList<Movies> {
            val moviesList = ArrayList<Movies>()
            moviesList.add(Movies("Night of the Comet", "1984", "Thom Eberhardt", "What would kids in the 1980s do if the apocalypse blew through the world without them noticing? Hang out at the mall, but of course. That’s the set-up for this very funny, quite dated horror-comedy, which begins when a quartet of adolescents lock themselves inside a projection booth at the mall’s multiplex. This somehow allows them to live through an extinction-level event of some sort, which has also left roaming bands of murderous mutants. Catherine Mary Stewart of the equally inexplicable Weekend at Bernie’s leads the film, but it’s a movie of mood more than substance ultimately. Does the wealth-fueled naiveté of the average white teenager survive in a vacuum? Does it go away when they are being hunted for sustenance? It’s an interesting to watch on these terms and when the zombies show up, director Thom Eberhardt adds menace and a tight feel for suspense to the action sequences. And if we’re being honest, it belongs on this list for its soundtrack alone. The rest of this is just whip cream and cherries.", "https://static1.colliderimages.com/wordpress/wp-content/uploads/2020/10/night-of-the-comet.jpg?q=50&fit=crop&w=750&dpr=1.5"))
            moviesList.add(Movies("Dead Snow", "2009", "Tommy Wirkola", "This splatter-fest puts a Nordic spin on the traditional zombie by adding in elements of the Draugr, an undead creature from Scandinavian folklore that fiercely protects its treasure horde. In the case of Dead Snow, these draugr happen to be former SS soldiers who terrorized a Norwegian town and looted their belongings, only to be done in or chased into the freezing mountains by the villagers themselves. Dead Snow gets originality points for this, for sure. It’s also a very funny, gory, and satisfyingly violent movie with elements of Evil Dead and “teen sex/slasher” flicks scattered throughout. And if you like it, there’s more where that came from in the sequel", "https://static1.colliderimages.com/wordpress/wp-content/uploads/2020/10/dead-snow-social-feature.jpg?q=50&fit=crop&w=750&dpr=1.5"))
            moviesList.add(Movies("Cemetery Man", "1994", "Michele Soavi", "Dellamorte descends into madness, and the further he falls the more Cemetery Man threatens to go off the rails, leaving logic behind in favor of a slipstream psychosis. The result is a bit of a mess without a plot to speak of, but a gloriously weird mess it is. Saturated with philosophy and offbeat humor, Cemetary Man is all about sex and death, friendship and deception; a surrealist, satirical and stylish trip to the brink loaded with splendid visuals and a knockout performance from Everett that takes him from a strapping hero to spitting psychopath.", "https://static1.colliderimages.com/wordpress/wp-content/uploads/2020/10/cemetary-man-social-feature.jpg?q=50&fit=crop&w=750&dpr=1.5"))
            moviesList.add(Movies("28 Weeks Later", "2007", "Juan Carlos Fresnadillo", "28 Days Later subverted the conventions of the zombie genre in such clever, convincing ways, it became the modern-day zombie template that countless films tried to mimic. 28 Weeks Later was smart enough not to follow the blueprint and flipped the script, depicting the British government's attempt to rebuild society in the aftermath of the rage virus and the subsequent outbreak that brings it all crashing down. Through the contained military facility we get to witness a small-scale version of the viral apocalypse that we missed in the first film and the desperate, hopeless attempts to stop it. That makes 28 Weeks Later is a bit more of a conventional zombie film, depicting the downfall of society and the breakdown of boundaries in times of terror, but it's a very good conventional zombie movie. Fresnadillo hits all the right notes, lacing the broad arc with intimate family drama and depending on his superb cast to sell every moment of heartbreak amidst the bloodshed.", "https://static1.colliderimages.com/wordpress/wp-content/uploads/2020/10/28-weeks-later.jpg?q=50&fit=crop&w=750&dpr=1.5"))
            moviesList.add(Movies("Night of the Creeps", "1986", "Fred Dekker", "A blunt-force display of Dekker’s sensibilities, Night of the Creeps is an exuberant blend of zombie genre trappings and the sci-fi B-movies of yore; like Mars Attacks by way of Night of the Living Dead. Dekker lines his film with loving references to the genre, most obviously with his characters, who he names after the horror greats: Romero, Raimi, Carpenter, Cronenberg, Cameron, Landis, and Hooper. Night of the Creeps feels like Dekker took all his favorite movies and stirred them together in a silly, slimy stew. It can be clunky and goofy, but Night of the Creeps wears its idol worship like a badge of honor and Dekker’s creative flourish is a firewall that keeps his homage from becoming derivative.", "https://static1.colliderimages.com/wordpress/wp-content/uploads/2020/10/night-of-the-creeps.jpg?q=50&fit=crop&w=750&dpr=1.5"))
            moviesList.add(Movies("ParaNorman", "2012", "Chris Butler, Sam Fell", "Rarely do zombies get the animated treatment (rarer still, stop-motion animation), and even if they do, they’re traditionally made the villains. LAIKA is anything but traditional, which makes their films so endearing, unique, and memorable. ParaNorman, one of the stop-motion studio’s handful of original films, manages to not only (re)animate some truly gruesome and decaying corpses, but to give them a voice and agency within the story. Most live-action movies can’t even achieve that much.", "https://static1.colliderimages.com/wordpress/wp-content/uploads/2020/10/paranorman-social-feature.jpg?q=50&fit=crop&w=750&dpr=1.5"))
            return moviesList
        }
    }

}