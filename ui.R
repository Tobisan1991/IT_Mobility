## ui.R ##
library(shinydashboard)
library(DT)

dashboardPage(
  dashboardHeader(title = "OLAP & DM"),
  dashboardSidebar(),
  dashboardBody(
    fluidRow(
      DT::dataTableOutput("salesTable")
    )
  )
)
