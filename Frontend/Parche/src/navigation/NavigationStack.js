import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import ParchesNavigation from "./ParchesNavigation";
import Parches from "../screen/Parches";
import ParchesInfo from "../screen/ParchesInfo";
import AddParche from "../screen/AddParche";

const Stack = createStackNavigator();

export default function NavigationStack() {
  return (
    <Stack.Navigator initialRouteName="Home">
      <Stack.Screen name="Parches" component={Parches} />
      <Stack.Screen name="parchesInfo" component={ParchesInfo} />
      <Stack.Screen name="addParche" component={AddParche} />
    </Stack.Navigator>
  );
}