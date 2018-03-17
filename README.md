# Prenda
Joebert S. Jacaba  
joebertj@gmail.com  
Github https://github.com/joebertj/prenda  
Openshift Demo https://openshift.kenchlightyear.com  
Sourceforge http://prenda.sourceforge.net  
Author https://ex.kenchlightyear.com  

## Overview

1. Container-ready - Tested on Openshift (Enterprise Kubernetes/Docker). Easily deployed to cloud for load balancing and high availability.
2. Highly secure - Uses Spring Security, Passay password validator and bcrypt password hash
3. Web-based - Unlimited branches possible, no setup necessary for branches (PCs at the branches will only need internet connection and a web-browser (Tested using Chrome)
4. Centralized Database, management and reporting - No need for main office to solicit reports from branches (the Head office can generate any report of any branch at any time), the main server will be secured at the client's main office
5. Real-time access and monitoring of activities - eliminates the possibility of fraudulent activities from branches
6. Granularized access control -  possible to set-up unlimited Encoder, Manager, Branch Owner and Administrator Accounts
7. Automated aging of pawned items
8. Highly configurable interest rates per Branch on a daily basis
9. Supports secure pullout of items from Branch (thru a Liaison account)
10. Supports inter-branch cash replenishments
11. Foreclosed items available for auction
12. Supports pawn renewals
13. Redeemed, Outstanding, Inventory, Foreclosed and Pulled-out item views
14. Verbose statistics organized through various time periods
15. Graphical activity reports
16. Daily cash position report and cash disbursements (Basic Accounting)
17. Customer Self-service - possible for customers to securely check status of pawned items and renew expiring items online 

## Prerequisites
### Server
#### v0.1.2 BETA and older
Apache Tomcat 5.5  
MySQL 5.0  
#### v0.1.3 BETA and newer
Apache Tomcat 8.0  
MySQL 5.7  
### Client
Chrome  

## Installation

1. Download Apache Tomcat 5 (tested on apache-tomcat-5.5.25)
	`tar xvzf apache-tomcat-5.5.25.tar.gz`
	
2. Download and Install Mysql 5 (tested on 5.0.51a)
	on command prompt,
		`mysql -u root -p`
	on sql> prompt,
```
		create database prenda;
		grant all privileges on prenda.* to 'prenda'@'localhost' identified by 'prenda';
		quit
```
3. Extract package
```
	tar xvzf prenda-0.1.1.tar.gz
	cd prenda
```
4. Copy prenda.war to webapps folder of Tomcat
	`cp prenda.war /path/to/tomcat/webapps`
	
5. On command prompt,
		`mysql -u prenda -p prenda < prenda.sql`

6. Go to bin folder of Tomcat
	`cd /path/to/tomcat/bin`
	on linux,
		`./startup.sh`
	on windows,
		`startup.bat`

7. Point your browser to http://localhost:8080/prenda (adjust port on Tomcat's server.xml)  
v0.1.2 BETA and older:		admin/123  
v0.1.3 BETA and newer:		owner/123  
v0.1.4 BETA and newer has a Self-Registration feature. Valid email needed to activate.  	
	
## Changelog

March 3, 2018  
Converted to Maven  

January 27, 2013  
Fixed prenda.sql to add required initial values.  

October 20, 2008  
Added reports.  
Various bug fixes.  

October 2, 2008  
Made mandatory for admin and owner to select pulled-out items for auction.  
Bug fixes on owner views.  

October 1, 2008  
Updated jasperreports to latest version.  
Bug fixes on disbursement.  

July 10, 2007  
Added yearly and monthly in redeem view.  

May 5, 2007  
Added yearly and monthly in pawn view.  

May 4, 2007  
Fixed a bug which when adding or updating a user with owner level, 
the owner of the branch becomes off sync.

May 3, 2007  
Added option to use previously used date for loan date
other than current date (default).  

May 2, 2007  
Added advance interest on pawn table  
Added name columns on users table  
Added loan date in users table  
Fixed pawn edit bug  

April 27, 2007  
Created roll over images for navigation menu  

March 22, 2007  
Modified logo to blend with background  

February 21, 2007  
Completed Branch Service  
Added Page Service  
Applied Page and Branch services to JSP pages  

February 19, 2007  
Added Cash Disbursements Report  
Added Account Service  

February 18, 2007  
Added PT number option in redeem  
Added Branch PID and PT number in pawn  
Added breakdown of redeem in cash position  

February 15, 2007  
Added overriding of loan date  
Added support for specifying pawn ticket start number  
Added logging facility  
Added new graphs  
Added new PDF for printing  
Added RSS support  
Collated classes to packages  
Used JavaBeans for separation of presentation from logic  

## License
GNU GENERAL PUBLIC LICENSE Version 3

## Todo

* SaaS features. Free-tier is 1 branch/owner/manager/encoder.
* Add power encoder role to be able to adjust dates.
* Adjustable redeem dates.
* Review interest rate of redeem.
* Adjustable auction markup (10%).
* Adjustable edit minute (15).
* Adjustable maturity (30 days) and expiry (120 days)
* Name entry for user.
* User should not be able to delete or archive himself. (unit test)
* Admin should have unarchive functionality.
* Eliminate all sql:query and convert to JavaBeans.
* Displaytag-like functionality
