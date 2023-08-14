import React from "react";
import { createStackNavigator } from "@react-navigation/stack";

import Parches from "../screen/Parches";
import ParchesInfo from "../screen/ParchesInfo";
import AddParche from "../screen/AddParche";
import AddAsistente from "../screen/AddAsistente";

 
export default  function ParchesNavigation() {

    const Stack = createStackNavigator();
    
    return (  
        <Stack.Navigator>

            <Stack.Screen
            name="Parches"
            component={Parches}
            options={{ title: "Parches", headerTransparent: true }}
            />
            <Stack.Screen
                name="parchesInfo"
                component={ParchesInfo}
                options={{ title: "parchesinfo", headerTransparent: true }}
            />
            <Stack.Screen
                name="addParche"
                component={AddParche}
                options={{ title: "addParches", headerTransparent: true }}
            />

            <Stack.Screen
                name="addAsistente"
                component={AddAsistente}
                options={{ title: "addAsistentes", headerTransparent: true }}
            />
        </Stack.Navigator>
    );
}
