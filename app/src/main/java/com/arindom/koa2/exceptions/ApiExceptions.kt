package com.arindom.koa2.exceptions

class ApiExceptions:Throwable("Something went wrong")
class NoDataFoundException(val name:String):Throwable(name)
object NoFavouriteFound : Throwable()