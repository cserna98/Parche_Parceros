import React, {useState, useEffect} from 'react';
import { SafeAreaView, Text } from "react-native";
import {getParcheById} from '../api/ParcheApi'

function ParchesInfo(props) {

    const [parche, setParche] = useState(null);

    const {
        navigation,
        route: {params},
      } = props;

        const fetchData = async (id) => {
        try {
            const response = await getParcheById(id);
            setParche(response);
        } catch (error) {
            console.log("no funcione me devuelvo yeiii");
            navigation.goBack();
        }
        };

        useEffect(() => {
            if (params) {
                console.log(params.id)
                fetchData(params.id);
            }
        }, [params]);

    return ( 
        <SafeAreaView>
            <Text>intentando</Text>
        </SafeAreaView>
     );
}

export default ParchesInfo;