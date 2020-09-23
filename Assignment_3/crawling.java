import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.URL;

import com.opencsv.CSVWriter;
import org.jsoup.UnsupportedMimeTypeException;
import java.net.*;

public class crawling {
    private static final List<String> faculty_Words= new LinkedList<>();
    public static boolean isValid(String url)
    {
        try {
            new URL(url).toURI();

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean page_related(Document page) {
        String string_from_page  = page.toString().toLowerCase();
        int words=0;
        for(String s : faculty_Words)
        {
            if(string_from_page.contains(s)) {
                words++;
            }
        }
        return words>=7;
    }

    public static boolean link_related(String link) {
        for(String s : faculty_Words)
        {
            if(link.contains(s) ) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Crawling started...");
        Queue<String> unique_links = new LinkedList<>();
        Set<String> unique_links_set = new HashSet<String>();
        Set<String> downloads = new HashSet<String>();
      //  List<String> faculty_words = new LinkedList<>();
        faculty_Words.addAll(Arrays.asList(("faculty professor prof assistant teacher teaching engineer doctor scholar chairman qualification director phd ").split(" ")));

        Document document;
        Document document2;
        unique_links.add("https://pec.ac.in/");
        int count=0;
        try {
        FileOutputStream fout = new FileOutputStream("pec_links.csv");        //created a file for links
        CSVWriter csv = new CSVWriter(new FileWriter(new File("pec_links.csv")));
        FileOutputStream fout_1 = new FileOutputStream("object_links.csv");        //created a file for links
        CSVWriter csv_1 = new CSVWriter(new FileWriter(new File("object_links.csv")));
        csv.writeNext(new String[] {"link", "text", "paras"});
        csv_1.writeNext(new String[] {"object link", "text"});
        Iterator<String> itr = unique_links.iterator();
        while (itr.hasNext()) {
            String top_link=unique_links.remove();
            try {
                document = Jsoup.connect(top_link).get();
                if (link_related(top_link) || page_related(document)) {
                    Elements paras = document.select("p");
                    String all_paras = "";
                    for (Element x : paras) {
                        all_paras += x.text();
                    }
                    count++;
                    System.out.println(count+ " " + top_link);
                    csv.writeNext(new String[] { top_link, document.title() ,  all_paras});

                }
                Elements links = document.select("a[href]");        //extracted all links
                for (Element link : links) {
                    String temp_link = link.attr("abs:href");
                    if (isValid(temp_link)) {
                        if (temp_link.startsWith("https://pec.ac.in/") && !(temp_link.contains("#"))) {
                            if (!unique_links_set.contains(temp_link)) {
                                try {
                                    Jsoup.connect(temp_link).get();
                                    unique_links.add(temp_link);
                                }
                                catch(UnsupportedMimeTypeException type) {
                                    downloads.add(temp_link + link.text() + "\r\n");
                                    csv_1.writeNext(new String[] { temp_link, link.text()});
                                }

                            }
                            unique_links_set.add(temp_link);
                        }
                    }
                }
                System.out.println(unique_links);
                if (count == 1000)
                    break;
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
//!(temp_link.endsWith(".PDF")) &&URL url = new URL(temp_link);
//                    URLConnection u = url.openConnection();
//                    String type = u.getHeaderField("Content-Type");
//!(temp_link.endsWith(".PDF") || temp_link.endsWith(".pdf") || temp_link.endsWith(".jpg") || temp_link.endsWith(".JPG") || temp_link.endsWith(".docx") || temp_link.endsWith(".doc"))