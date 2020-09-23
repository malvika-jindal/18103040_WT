External Libraries used:
jsoup-1.13.1.jar
opencsv-5.2.jar 

Input given: None

Base link given to crawler: https://pec.ac.in/
Conditions:
A link is said to be related to faculty when its html page consists of more than 7 words related to faculty or 
the link itself contains any word related to faculty.

Output:
1. Pec_links.csv : Consists of 1000 links related to faculty along with their title and p tags.
2. Object_links.csv: Consists of all links having .pdf, .jpg, .sheet, .docx , etc extensions along with their titles.

In console:
Set of unique links and links same as in the pec_links.csv file
