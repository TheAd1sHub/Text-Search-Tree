return
{
    -- Data request messages
    request_message_beginning = "Please, state the ",

    local_file_path_request = request_message_beginning .. "path to the text file.",
    web_page_url_request = request_message_beginning .. "URL of the source website.",
    sought_for_token_request = request_message_beginning .. "sought-for word.",

    -- Error notifications
    error_message_beginning = "An error occurred!\n",

    invalid_input_notification = error_message_beginning .. "Unfortunately, we cannot interpret your input.\nPlease, check its consistency and try again.",
    reading_session_failure_notification = error_message_beginning .. "Attempt to receive data from the given source resulted in a failure.\nPlease, try again later!",
    unknown_error_notification = error_message_beginning .. "Something went wrong whilst processing your request.\nPlease, check the consistency of your input or try again later!"
}