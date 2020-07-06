library(dplyr)


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

}


objects_with_incredibly_long_names_in_fact_so_long_it_does_not_make_sense <- function() {

}


equals_na <- function() {
    x <- NA
    if (x == NA) {}
}


extraction_operator <- function() {
    x <- list(one=1, two=2)
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
    1+2*3-4/5%/%6
}


line_length <- function() {
    #Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud...
}


open_curly_brace <- function()
{}


pipe_continuation <- function() {
    x <- 2
    x %>% max() %>% min() %>%
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
