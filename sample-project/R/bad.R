
T_and_F <- function() {
    F
    T
}


equal_sign_assignment <- function() {
    my_var = 0L
}


spaces_around_commas <- function() {
    c(1L ,2L)
}


commented_code <- function() {
    #x <- c(1, 2, 3)
}


objectNamingStyle <- function() {
    my.var <- 0L
}


equals_na <- function() {
    x <- NA
    if (x == NA) {}
}


extraction_operator <- function() {
    x <- list(one = 1L, two = 2L)
    x[1L]
    x$one
}


space_before_function_parenthesis <- function() {
    list (1L, 2L)
}


implicit_integer <- function() {
    1
}


infix_operator_spaces <- function() {
    1L+2.0*3L-4L/5L%/%6.0
}


open_curly_brace <- function()
{}


pipe_continuation <- function() {
    x <- 2.0
    x %>% max()%>% min() %>%
        mean()
}


use_seq <- function() {
    1L:length(c(1L, 2L, 3L))
}


trailing_whitespace <- function() { 
    
}


unneeded_concatenation <- function() {
    c()
}


spaces_around_code_in_parenthesis <- function() {
    x <- c(1L, 2L, 3L)
    x[[ 1L ]]
}


use_double_quotes <- function() {
    'Hello World'
}


space_before_left_parenthesis <- function() {
    if(TRUE){
        1L
    }
}
