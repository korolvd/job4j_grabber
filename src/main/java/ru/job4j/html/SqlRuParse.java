package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.SqlRuDateTimeParse;

import java.time.LocalDateTime;
import java.util.List;

public class SqlRuParse {

    public Post postDetails(String url) throws Exception {
        Post post = new Post();
        Document doc = Jsoup.connect(url).get();
        Element footer = doc.select(".msgFooter").get(0);
        Element description = footer.parent().parent().child(1).child(1);
        LocalDateTime created = new SqlRuDateTimeParse().parse(footer.text().split(" \\[")[0]);
        post.setCreated(created);
        post.setDescription(description.text());
        return post;
    }

    public static void main(String[] args) throws Exception {
        String url = "https://www.sql.ru/forum/job-offers";
        List<String> pages = List.of("", "/2", "/3", "/4", "/5");
        for (String page : pages) {
            Document doc = Jsoup.connect(url + page).get();
            Elements row = doc.select(".postslisttopic");
            for (Element post : row) {
                Element parent = post.parent();
                System.out.print(parent.children().get(5).text() + " ");
                System.out.println(post.child(0).text());
                System.out.println(post.child(0).attr("href"));
            }
        }
    }
}
