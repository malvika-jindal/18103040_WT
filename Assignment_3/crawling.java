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

    /*to check the validity of the string*/
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

    /* if the html page contains more than
        7 words related to faculty then that
        link is considered to be added.
     */
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

    /*If the link itself contains any faculty
    related words then that link is considered
    to be added.
     */
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

        /* queue is used to perform BFS type iteration */
        Queue<String> unique_links = new LinkedList<>();

        /* set to check that unique links are only considered */
        Set<String> unique_links_set = new HashSet<String>();

        /*set to check all unique downloadable links are considered*/
        Set<String> downloads = new HashSet<String>();

        /* array of words that are related to faculty*/
        faculty_Words.addAll(Arrays.asList(("faculty professor prof assistant teacher teaching engineer doctor scholar chairman qualification director phd ").split(" ")));

        Document document;
        unique_links.add("https://pec.ac.in/");
        unique_links_set.add("https://pec.ac.in/");
        int count=0;
        try {

        FileOutputStream fout = new FileOutputStream("pec_links.csv");        //created a file for faculty related links
        CSVWriter csv = new CSVWriter(new FileWriter(new File("pec_links.csv")));

        FileOutputStream fout_1 = new FileOutputStream("object_links.csv");    //created a file for links that has pdf,docx,sheet,xls,etc
        CSVWriter csv_1 = new CSVWriter(new FileWriter(new File("object_links.csv")));

        csv.writeNext(new String[] {"link", "text", "paras"});
        csv_1.writeNext(new String[] {"object link", "text"});

        Iterator<String> itr = unique_links.iterator();
        while (itr.hasNext()) {
            String top_link=unique_links.remove();
            try {
                document = Jsoup.connect(top_link).get();

                /* if the link is related only then the p tags are extracted*/
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

                /* Get all the sublinks from the current link*/
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
                                    /* If the content-type is not text/html then
                                    this exception is thrown and the object links that
                                    contain .pdf, .jpg, .docx links are added
                                     */
                                    if(!downloads.contains(temp_link)) {
                                        csv_1.writeNext(new String[]{temp_link, link.text()});
                                        downloads.add(temp_link + link.text() + "\r\n");
                                    }
                                }
                            }
                            unique_links_set.add(temp_link);
                        }
                    }
                }

                /* if the unique links exceed 1000 then crawler stops*/
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
