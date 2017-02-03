# RSS parser programming challenge

## Welcome

Dear Candidate - first of all thank you for participating and accepting the challenge. We appreciate your efforts and hope to soon meet you in person.

## General rules

There are not many rules, the few are there to provide a ligth framework and to help us assess your workflow and mindset.
Please observe the following:

- The solution will need to be implemented in the Java language (1.7 or later)
- We ask you to fork this project and create a pull request when you are done
- Please be honest to us and to yourself and do not spend more than 8 hours of work on this project excluding research
- You are free to use any Java libraries
- You are free to structure the project as you like
- You can choose your build/project management system - we recommend you use Maven, if you use anything else please provide build instructions in a separate text file
- Commit small, commit often

## The task at hand

The challenge is to parse the provided legacy RSS feed and generate two JSON files that can be compared to the reference JSON files provided in the project. The RSS feed is a list of planned and unplanned outages that is affecting the company's networks. 
Outages.xml is the feed itself - it contains test data that needs to be processed and separated out into a customer outages and business outages json file. The two included JSON files are for reference - so please don't overwrite them. You can use them to compare against your own generated JSON files. The language of the RSS file is Dutch, but reading it should not pose a problem as the XML structure itself is in English. It is a real world example therefore a realistic programming situation at our company.

## The logic that needs to be implemented for a successful conversion:

- If the value of the tag <james:locations> contains either ZMST or ZMOH the outage item will be in the business_outages.json
- The <description> tag contains the beginning (Starttijd:) and the end (Eindtijd:) of the outage - you will need to parse those out of the field
- If either the beginning (Starttijd:) or the end (Eindtijd:) is "onbekend" then the mapping startDate and/or endDate will need to have the value "Onbekend" in the output JSON
- The status field in the output JSON files will need to conform to the following rules:
    - If the endDate is in the future or its value is "onbekend" the status field in the JSON should be: "Actueel"
    - If the startDate is in the future the status should be: "Gepland"
    - If the endDate is in the past the status should be: "Opgelost"

# Tips

- Choose your libraries right - if you get stuck with one, try another solution
- Parsing and producing correct JSON files is the most important, if you are running out of time, you can skip comparing your JSONs against the reference files
- If you have questions regarding the project or something is not clear you can let me know at marton dot tatai at kpn ot com

# Disclaimer

This programming challenge is one step in a multiple step hiring process. It is a means to assess the competency of our candidates.
The work you will do - including writing code and/or designing software - is NOT going to be reused, copied or in any form replicated by us or any of our third parties.
If you have any concerns - related to what is stated above or the programming challenge itself - please email me at marton dot tatai at kpn dot com.
