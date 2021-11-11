package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class SqlRuParse {
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
