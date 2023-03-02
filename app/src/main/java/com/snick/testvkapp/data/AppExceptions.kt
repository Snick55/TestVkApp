package com.snick.testvkapp.data

import java.lang.Exception

open class AppExceptions:Exception()

class NoInternetException: AppExceptions()

class SomethingWentWrongException(): AppExceptions()

class EmptyResponseException:Exception()

class NoSuchGifsExceptions: AppExceptions()