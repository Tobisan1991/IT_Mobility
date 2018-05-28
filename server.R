library(readr)
library(dplyr)
library(odbc)

## DB-Connection ##
adventureWorks <- DBI::dbConnect(
  odbc::odbc(),
  Driver = "/usr/local/Cellar/msodbcsql@13.1.9.2/13.1.9.2/lib/libmsodbcsql.13.dylib", 
  Database = "AdventureWorksDW2016CTP3",
  Server = "10.18.3.20",
  UID = "NguyenLV",
  PWD = "abcd1234$12"
)

## Data Prep ##
#sales = read_tsv("/Users/philipp/Dropbox/3.Semester/OLAP & DM/Dashboard/Data/Sales.tsv")
sales = adventureWorks %>% tbl("FactResellerSales") %>% head(100) %>%
  as.data.frame()
## server.R ##
function(input, output, session) {
  output$salesTable = DT::renderDataTable(sales)
}
