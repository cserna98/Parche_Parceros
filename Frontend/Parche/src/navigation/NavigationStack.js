import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import ParchesNavigation from "./ParchesNavigation";
import Parches from "../screen/Parches";
import ParchesInfo from "../screen/ParchesInfo";

const Stack = createStackNavigator();

export default function NavigationStack() {
  return (
    <Stack.Navigator initialRouteName="Home">
      <Stack.Screen name="Parches" component={Parches} />
      <Stack.Screen name="parchesInfo" component={ParchesInfo} />
    </Stack.Navigator>
  );
}