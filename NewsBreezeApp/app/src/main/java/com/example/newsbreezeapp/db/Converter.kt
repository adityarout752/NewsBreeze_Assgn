package com.example.newsbreezeapp.db

import androidx.room.TypeConverter
import com.example.newsbreezeapp.modelData.Source



class Converter{

 @TypeConverter
 fun fromSource(source: Source):String{
  return source.name  // converts to string
 }

 @TypeConverter
 fun toSource(name:String):Source{
  return Source(name,name) //converts to source
 }
}