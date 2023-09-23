import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { useNavigation } from '@react-navigation/native';
import ParchesNavigation from "./ParchesNavigation";
import Parches from "../components/Parches";
import ParchesInfo from "../screen/ParchesInfo";
import AddParche from "../screen/AddParche";
import AddAsistente from "../screen/AddAsistente";
import AddItem from "../screen/AddItem";
import Items from "../screen/Items"
import AsistenteInfo from "../screen/AsistenteInfo";
import ItemInfo from "../screen/ItemInfo"
import Login from "../screen/Login";
import MainScreen from "../screen/MainScreen";
import Registro from "../screen/Registrousuario";


const Stack = createStackNavigator();


export default function NavigationStack() {

  
  return (
    <Stack.Navigator initialRouteName="Home">

      
      <Stack.Screen name="Login" component={Login} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }} />

      <Stack.Screen name="Registro" component={Registro} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }} />
      
      <Stack.Screen name="Parches" component={MainScreen} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }} />

      

      <Stack.Screen name="parchesInfo" component={ParchesInfo} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }} />

      <Stack.Screen name="addParche" component={AddParche} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }}/>

      <Stack.Screen name="addAsistente" component={AddAsistente} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }} />

      <Stack.Screen name="addItem" component={AddItem} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }}/>

      <Stack.Screen name="Items" component={Items} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }}/>

      <Stack.Screen name="asistenteInfo" component={AsistenteInfo} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }}/>

      <Stack.Screen name="Item Info" component={ItemInfo} options={{
            headerShown: false, // Oculta el header en todas las pantallas de Parches
          }}/>
      
          
    </Stack.Navigator>
  );
}