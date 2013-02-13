# [Storecloud]()

**Storecloud** is an online store application written with Grails 2.0. The application architecture is a multitenant SaaS online store (each store is related to a user account). It uses Paypal as a mean of payment (credit card payment).

I was working on this project for personal use: a friend and I wanted to create a startup with this concept, but I don't have time with my PhD.

## Quick start

### Dependencies
* [Grails](http://grails.org/) (2.1.0 tested)
* [Spring security plugin](http://grails.org/plugin/spring-security-core)
* [Multi-tenant-single-db plugin](http://multi-tenant.github.com/grails-multi-tenant-single-db/)

You only need Grails on your computer. If you don't have grails, [here](http://grails.org/doc/latest/guide/gettingStarted.html#requirements) are the instructions. When the installation is done, you may read the next step!

### Initializing the project
* Download the project:

```
$ git clone https://github.com/badock/storecloud.git
```

* Upgrade the application to you version of Grails:


```
grails upgrade
```

* Run the application:


```
grails run-app
```

### Sample application

There is a proof of concept of this project that is now running: two applications are running: app1 and app2.

Please had the following lines in you /etc/hosts file:

```bash
127.0.0.1       app1.shoppycloud.com
127.0.0.1       app2.shoppycloud.com
```

then if you visit http://app1.shoppycloud.com:8080 with your navigator you will get:

![app1 home screen](https://raw.github.com/badock/storecloud/master/img_github/app1_home.png "home screen of app1")

and if you visit http://app2.shoppycloud.com:8080 with your navigator you will get:

![app2 home screen](https://raw.github.com/badock/storecloud/master/img_github/app2_home.png "home screen of app2")

### Administration of an application

Click on login to sign in. For both application the admin username is ***admin*** and the password is ***admin***

The administration part enables administrators to manage:

* shop information (the paypal account that will be credited)
* classes of products
* products
* customers information

Here are some screens of the administration section

![admin screen](https://raw.github.com/badock/storecloud/master/img_github/app2_admin_shop.png "admin screen")

![admin screen](https://raw.github.com/badock/storecloud/master/img_github/app2_edit_product.png "admin screen")

![admin screen](https://raw.github.com/badock/storecloud/master/img_github/app2_edit_classes.png "admin screen")
