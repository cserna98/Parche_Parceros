import React from 'react';
import  { useState, useEffect } from "react";
import { SafeAreaView, Text,Button } from "react-native";
import { ParchesApi } from '../api/ParcheApi';
import  ParcheList  from '../components/ParcheList'
import { useNavigation } from "@react-navigation/native";

function Parches() {

    const [parches, setParches] = useState([]);

    const navigation = useNavigation(); 

    const goToAddParche=()=>{
        navigation.navigate("addParche");
        };


    const loadParche = async () => {
        console.log("SI ENTRO A LOAD")
        try {
            const response = await ParchesApi();
            console.log(response)
            console.log(response[0].asistente)
            const parchesList =[];
            for await (const parche of response){
        
                parchesList.push({
                    id: parche.id,
                    nombre: parche.nombre,
                    fecha: parche.fecha,
                    dias: parche.dias,
                })
            }
            setParches([...parches, ...parchesList])

        } catch (error) {
            console.error(error);
        }
    }


    useEffect(()=>{
        loadParche();
    },[])

    return ( 
        <SafeAreaView>
           <ParcheList
            parches={parches}
           />
           <Button title="+ Parche" onPress={goToAddParche}/>
        </SafeAreaView>

    );
}

export default Parches;