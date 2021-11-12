package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        List<String> pages = List.of("", "/2", "/3", "/4", "/5");
        try {
            for (String page : pages) {
                Document doc = Jsoup.connect(link + page).get();
                Elements rows = doc.select(".postslisttopic");
                rows.forEach(r -> list.add(details(r.child(0).attr("href"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Post details(String link) {
        Post post = new Post();
        try {
            Document doc = Jsoup.connect(link).get();
            Element footer = doc.select(".msgFooter").get(0);
            Element title = footer.parent().parent().child(0).child(0);
            Element description = footer.parent().parent().child(1).child(1);
            LocalDateTime created = dateTimeParser.parse(footer.text().split(" \\[")[0]);
            post.setTitle(title.text());
            post.setLink(link);
            post.setDescription(description.text());
            post.setCreated(created);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }
}
