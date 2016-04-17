---
layout: post
title: "Mongo backup"
description: "how to backup db in Mongo"
category: 
tags: [dba]
---

* mongoimport
* mongoexport
* mongorestore
* mongodump


# mongoimport

The mongoimport tool imports content from an Extended JSON, CSV, or TSV export created by
mongoexport.


## Import JSON to Remote Host Running with Authentication

```
mongoimport --host mongodb1.example.net --port 37017 --username user --password pass --collection contacts --db marketing --file /opt/backups/mdb1-examplenet.json
```

## CSV Import
```
mongoimport --db users --collection contacts --type csv --headerline --file /opt/backups/contacts.csv
```


# mongoexport
is a utility that produces a JSON or CSV export of data stored in a MongoDB instance.

## Export from Remote Host Running with Authentication


```
mongoexport --host mongodb1.example.net --port 37017 --username user --password pass --collection contacts --db marketing --out mdb1-examplenet.json
```

## Export Query Results

```
mongoexport --db sales --collection contacts --query '{"field": 1}'
```

## Export in CSV Format
```
mongoexport --db users --collection contacts --type=csv --fields name,address --out /opt/backups/contacts.csv
```

Using the --fieldFile option, specify the fields to export with the file.


## Export Query Results

```
mongoexport --db sales --collection contacts --query '{"field": 1}'
```

You must enclose the query in single quotes (e.g. ') to ensure that it does not interact with your shell environment.

