chatty-core
========
Core plugin that provides basic functionality for [Chatty](https://github.com/bogeymanEST/chatty)

Commands
========
The core plugin provides the following commands:

`!help (command)`
> If `command` is omitted, shows a list of all visible commands. Otherwise, shows the usage and parameter descriptions of the given command.

`!info`
> Displays information about Chatty: the number of running bots and plugins.

`!register [username] [password]`
> Creates a new account with the given username and password for the user that executed the command.<br/>
> The command can only be used in private messages.

`!login [username] [password]`
> Logs a user in with the given username or password.<br/>
> The command can only be used in private messages.
