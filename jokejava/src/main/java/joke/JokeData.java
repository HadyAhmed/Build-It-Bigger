package joke;


import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class JokeData {

    private final List<String> list = asList("A man receives a phone call from his doctor.\n" +
                    "The doctor says, \"I have some good news and some bad news.\"\n" +
                    "The man says, \"OK, give me the good news first.\"\n" +
                    "The doctor says, \"The good news is, you have 24 hours to live.\"\n" +
                    "The man replies, \"Oh no! If that's the good news, then what's the bad news?\"\n" +
                    "The doctor says, \"The bad news is, I forgot to call you yesterday.\"",
            "A person who speaks two languages is bilingual...A person who speaks three languages is trilingual...A person who speaks four or more languages is multilingual. \n" +
                    "What is a person who speaks one language? \n" +
                    "An American",
            "\"Do you know what really amazes me about you?\" \n" +
                    "\"No.What?\" \n" +
                    "\"Oops.Sorry. I was thinking about someone else!\" ",
            "Why do we park our car in the driveway and drive our car on the parkway? ",
            "You can use this joke to explain that insulting someone is considered funny especially when that person is fishing for a compliment.\n" +
                    "Mary: John says I'm pretty. Andy says I'm ugly. What do you think, Peter? \n" +
                    "Peter: I think you're pretty ugly."
    );

    public String getJoke() {
        return list.get(new Random().nextInt(list.size()));
    }
}
