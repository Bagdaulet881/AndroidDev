package com.example.instabybakosta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Database {

    //----------------------------------------------------------------------------------------------
    public static String makeRandomLogin(){
        String ch = "abcdefghijklmnopqrstuvwxyz";
        String login = "";
        int length = 5;
        Random rand = new Random();

        char[] text = new char[length];
        for (int i=0; i<length; i++){
            text[i] = ch.charAt(rand.nextInt(ch.length()));
        }
        for(int i = 0; i < text.length; i++){
            login +=text[i];
        }
        return login;
    }






    //----------------------------------------------------------------------------------------------

    public Database(){
        postUrls.add("https://garage-news.com/wp-content/uploads/2019/06/bmw-7series-sedan-inspire-sp-desktop.jpg.asset_.1544526748115-1200x750.jpg");

        postUrls.add("https://pm1.narvii.com/6894/996be3171d4c31dabd6f88255660adc240fdfe16r1-583-850v2_hq.jpg");
        postUrls.add("https://cdn.wallpapersafari.com/29/46/3ihoO8.jpg");
        postUrls.add("https://blazonsart.com/scraps/greetings_wishes/nature/nature-47.jpg");
        postUrls.add("https://i1.sndcdn.com/artworks-000395642361-4m877b-t500x500.jpg");
        postUrls.add("https://i1.sndcdn.com/avatars-000034198762-fk2ytt-t500x500.jpg");
        postUrls.add("https://www.meme-arsenal.com/memes/f3bd7e6098536cad23990ff7292509a5.jpg");
        postUrls.add("https://www.meme-arsenal.com/memes/6f18fe47f3ed6eeafb36fa555cd19601.jpg");
        postUrls.add("https://www.fosi.org/media/images/22601782810_cbe3ede5f5_o.focus-none.original.jpg");
        postUrls.add("https://www.fosi.org/media/images/22601782810_cbe3ede5f5_o.focus-none.original.jpg");
        postUrls.add("https://i.imgflip.com/1etozv.jpg");
        postUrls.add("https://pm1.narvii.com/6894/996be3171d4c31dabd6f88255660adc240fdfe16r1-583-850v2_hq.jpg");
        postUrls.add("https://cdn.wallpapersafari.com/29/46/3ihoO8.jpg");
        postUrls.add("https://blazonsart.com/scraps/greetings_wishes/nature/nature-47.jpg");
        postUrls.add("https://i1.sndcdn.com/artworks-000395642361-4m877b-t500x500.jpg");
        postUrls.add("https://i1.sndcdn.com/avatars-000034198762-fk2ytt-t500x500.jpg");
    //----------------------------------------------------------------------------------------------
        avaUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/BMW_logo.svg/500px-BMW_logo.svg.png");

        avaUrls.add("https://previews.123rf.com/images/profshop/profshop1611/profshop161100014/67808755-vector-lol-happy-guy-meme-face-for-any-design-isolated-eps-10.jpg");
        avaUrls.add("https://cdn2.vectorstock.com/i/1000x1000/35/31/frog-meme-face-for-any-design-isolated-eps-vector-11133531.jpg");
        avaUrls.add("https://res.cloudinary.com/teepublic/image/private/s--nkZEzGcY--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_jpg,h_630,q_90,w_630/v1567483821/production/designs/5824395_0.jpg");
        avaUrls.add("https://brandslogo.net/wp-content/uploads/2015/07/minions-film-logo.png");
        avaUrls.add("https://ih1.redbubble.net/image.330661891.3438/ap,550x550,12x12,1,transparent,t.png");
        avaUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Windows_logo_-_2012.svg/1200px-Windows_logo_-_2012.svg.png");
        avaUrls.add("https://i.pinimg.com/originals/dd/64/da/dd64da585bc57cb05e5fd4d8ce873f57.png");
        avaUrls.add("https://www.imaginedragonsmusic.com/sites/g/files/aaj7266/f/styles/suzuki_breakpoints_image_mobile-md_sq/public/release/201810/5232b1dcb43188537b7f9f1e77cad1f9a38870d1.jpg?itok=u3EZe7j8");
        avaUrls.add("https://media.hoodiego.com/unsafe/600x600/img.hoodiego.com/design/600/600/0a0909/45228/fbd67ec1c49c263ef0657b96090149f8.png.jpg");
        avaUrls.add("https://brandslogo.net/wp-content/uploads/2015/07/minions-film-logo.png");
        avaUrls.add("https://ih1.redbubble.net/image.330661891.3438/ap,550x550,12x12,1,transparent,t.png");
        avaUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Windows_logo_-_2012.svg/1200px-Windows_logo_-_2012.svg.png");
        avaUrls.add("https://i.pinimg.com/originals/dd/64/da/dd64da585bc57cb05e5fd4d8ce873f57.png");
        avaUrls.add("https://www.imaginedragonsmusic.com/sites/g/files/aaj7266/f/styles/suzuki_breakpoints_image_mobile-md_sq/public/release/201810/5232b1dcb43188537b7f9f1e77cad1f9a38870d1.jpg?itok=u3EZe7j8");
        avaUrls.add("https://media.hoodiego.com/unsafe/600x600/img.hoodiego.com/design/600/600/0a0909/45228/fbd67ec1c49c263ef0657b96090149f8.png.jpg");
    }

    public static List<News> newsGenerator() {
        String loremIpsum = "Lorem ummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        for (int i = 0; i < postUrls.size(); i++) {
        String urlPost = postUrls.get(i);
        String urlAva = avaUrls.get(i);
        int rand = (int)(Math.random()*1000);
        String likes = Integer.toString(rand);
        String comments = Integer.toString(rand/2);
        String login = makeRandomLogin();
        String txt = "<b>" + login + "</b>" + " " + loremIpsum;
            News news = new News(
                    "June 2" + i,
                    "<b>" + login + "</b>",
                    txt,
                    likes + " likes",
                    "View al " + comments + " comments",
                    urlPost,
                    urlAva
            );
            items.add(news);
        }
        return items;
    }

    //----------------------------------------------------------------------------------------------
    public static Vector<String> postUrls = new Vector<String>();
    public static Vector<String> avaUrls = new Vector<String>();
    public static List<News> items = new ArrayList<>();
    public static List<News> likedNews = new ArrayList<>();


}
