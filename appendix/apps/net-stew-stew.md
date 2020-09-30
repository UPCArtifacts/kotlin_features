## net-stew-stew
----
#### Metrics provided by Detekt
* Number of lines of code 988
* Number of Kotlin files: 13
* Cyclomatic complexity: 170
* Cyclomatic complexity by thousands of lines: 303 

----
**14** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#func_with_default_value">Function with Default Value</a> 
*	<a href="#singleton">Singleton</a> 
*	<a href="#smart_cast">Smart Cast</a> 
*	<a href="#data_class">Data Class</a> 
*	<a href="#func_call_with_named_arg">Function call with Named Argument</a> 
*	<a href="#property_delegation">Property Delegation</a> 
*	<a href="#sealed_class">Sealed Class</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('5e-05x%5E3%20&plus;-0.018219x%5E2%20&plus;%202.184126x%20&plus;%2011.9048',))
    * **R_Squared:** 0.97627886
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?22.595979%5Clog_%7B3.00495%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.91335991
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.531914x%20&plus;%2043.468301)
    * **R_Squared:** 0.75861739
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-32.948413%7D%7B1%20&plus;%20%5Cepsilon%5E%28--63.103643%28x%20-72.959438%29%29%7D%20&plus;%2097.365079)
    * **R_Squared:** 0.46952732

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T11_3](../plots/net-stew-stew_type_inference_T11_3.png)
![net-stew-stew T6](../plots/net-stew-stew_type_inference_T6.png)
![net-stew-stew T1](../plots/net-stew-stew_type_inference_T1.png)
![net-stew-stew T7](../plots/net-stew-stew_type_inference_T7.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?7.998558%5Clog_%7B3.434109%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.91798956
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.158273x%20&plus;%2014.489216)
    * **R_Squared:** 0.75527707
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-11.582771%7D%7B1%20&plus;%20%5Cepsilon%5E%28--51.462794%28x%20-41.986457%29%29%7D%20&plus;%2028.851064)
    * **R_Squared:** 0.55093295

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T6](../plots/net-stew-stew_lambda_T6.png)
![net-stew-stew T1](../plots/net-stew-stew_lambda_T1.png)
![net-stew-stew T7](../plots/net-stew-stew_lambda_T7.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?124.663368x%5E%7B1.17507%7D%20&plus;%201.913261)
    * **R_Squared:** 0.68184499
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.015376x%20&plus;%201.16732)
    * **R_Squared:** 0.26447214
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.105773%5Clog_%7B7.070938%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.22119109

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T4](../plots/net-stew-stew_safe_call_T4.png)
![net-stew-stew T1](../plots/net-stew-stew_safe_call_T1.png)
![net-stew-stew T6](../plots/net-stew-stew_safe_call_T6.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.041629%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.989502%28x%20-107.876553%29%29%7D%20&plus;%202.634488)
    * **R_Squared:** 0.59432766
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?105.819909x%5E%7B1.050784%7D%20&plus;%202.615986)
    * **R_Squared:** 0.38232879
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.018449x%20&plus;%201.993491)
    * **R_Squared:** 0.21571512
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000042%5Clog_%7B3.943439%7D%28x%29%20&plus;%200.379485)
    * **R_Squared:** 0.19756349

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T7](../plots/net-stew-stew_when_expr_T7.png)
![net-stew-stew T4](../plots/net-stew-stew_when_expr_T4.png)
![net-stew-stew T1](../plots/net-stew-stew_when_expr_T1.png)
![net-stew-stew T6](../plots/net-stew-stew_when_expr_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.13944%5Clog_%7B3.686375%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.35621674
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.070259x%20&plus;%2010.687255)
    * **R_Squared:** 0.19376499
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-12.108527%7D%7B1%20&plus;%20%5Cepsilon%5E%28--59.983568%28x%20-7.027011%29%29%7D%20&plus;%2016.108527)
    * **R_Squared:** 0.17444183

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T6](../plots/net-stew-stew_unsafe_call_T6.png)
![net-stew-stew T1](../plots/net-stew-stew_unsafe_call_T1.png)
![net-stew-stew T7](../plots/net-stew-stew_unsafe_call_T7.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.982914%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.041427%28x%20-53.775087%29%29%7D%20&plus;%200.06034)
    * **R_Squared:** 0.91008578
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.030085x%20&plus;%200.382775)
    * **R_Squared:** 0.89672478
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000945%5Clog_%7B4.78514%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.5872016

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T7](../plots/net-stew-stew_companion_object_T7.png)
![net-stew-stew T1](../plots/net-stew-stew_companion_object_T1.png)
![net-stew-stew T6](../plots/net-stew-stew_companion_object_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.032977%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.676749%28x%20-13.435382%29%29%7D%20&plus;%200.969246)
    * **R_Squared:** 0.98231627
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000134%5Clog_%7B7.996875%7D%28x%29%20&plus;%200.91881)
    * **R_Squared:** 0.6338136
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.007826x%20&plus;%202.277747)
    * **R_Squared:** 0.28583864

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T7](../plots/net-stew-stew_string_template_T7.png)
![net-stew-stew T6](../plots/net-stew-stew_string_template_T6.png)
![net-stew-stew T1](../plots/net-stew-stew_string_template_T1.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.923077%7D%7B1%20&plus;%20%5Cepsilon%5E%28-40.196218%28x%20-130.768794%29%29%7D%20&plus;%201.076923)
    * **R_Squared:** 0.60856865
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?131.409517x%5E%7B1.408349%7D%20&plus;%201.071839)
    * **R_Squared:** 0.48195064
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.00389x%20&plus;%200.871732)
    * **R_Squared:** 0.12867612
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.57477%5Clog_%7B131.779684%7D%28x%29%20&plus;%200.672385)
    * **R_Squared:** 0.06917607

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T9](../plots/net-stew-stew_func_with_default_value_T9.png)
![net-stew-stew T4](../plots/net-stew-stew_func_with_default_value_T4.png)
![net-stew-stew T1](../plots/net-stew-stew_func_with_default_value_T1.png)
![net-stew-stew T6](../plots/net-stew-stew_func_with_default_value_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?1.0x%5E%7B0.0%7D%20&plus;%200.0)
    * **R_Squared:** 1.0
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.000332x%20&plus;%200.029851)
    * **R_Squared:** 0.02222222
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B32592.604931%7D%28x%29%20&plus;%200.007463)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T5](../plots/net-stew-stew_singleton_T5.png)
![net-stew-stew T2](../plots/net-stew-stew_singleton_T2.png)
![net-stew-stew T6](../plots/net-stew-stew_singleton_T6.png)
### <a name="smart_cast">Smart Cast</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.007167x%5E3%20&plus;-0.196508x%5E2%20&plus;%201.034715x%20&plus;%203.885621',))
    * **R_Squared:** 0.90315816
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000387x%5E4%20&plus;%200.021872x%5E3%20&plus;-0.379558x%5E2%20&plus;%201.858244x%20&plus;%202.915033)
    * **R_Squared:** 0.90905242
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.412797x%20&plus;%206.254902)
    * **R_Squared:** 0.7938398
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?118.976414x%5E%7B0.974899%7D%20&plus;%20-13.975189)
    * **R_Squared:** 0.79788091
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2.722029%7D%28x%29%20&plus;%202.333333)
    * **R_Squared:** 0.0

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T11_3](../plots/net-stew-stew_smart_cast_T11_3.png)
![net-stew-stew T11_4](../plots/net-stew-stew_smart_cast_T11_4.png)
![net-stew-stew T2](../plots/net-stew-stew_smart_cast_T2.png)
![net-stew-stew T5](../plots/net-stew-stew_smart_cast_T5.png)
![net-stew-stew T6](../plots/net-stew-stew_smart_cast_T6.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-0.911765%7D%7B1%20&plus;%20%5Cepsilon%5E%28-51.302335%28x%20-34.815172%29%29%7D%20&plus;%201.911765)
    * **R_Squared:** 0.88571429
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?10.248107x%5E%7B0.97754%7D%20&plus;%200.842416)
    * **R_Squared:** 0.5844207
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.007321x%20&plus;%201.729412)
    * **R_Squared:** 0.46936392
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2.817589%7D%28x%29%20&plus;%201.227941)
    * **R_Squared:** 0.0

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T10](../plots/net-stew-stew_data_class_T10.png)
![net-stew-stew T5](../plots/net-stew-stew_data_class_T5.png)
![net-stew-stew T2](../plots/net-stew-stew_data_class_T2.png)
![net-stew-stew T6](../plots/net-stew-stew_data_class_T6.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?113.178098x%5E%7B1.034466%7D%20&plus;%201.71545)
    * **R_Squared:** 0.6095425
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.012389x%20&plus;%201.327909)
    * **R_Squared:** 0.52707228
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.961532%5Clog_%7B6.836587%7D%28x%29%20&plus;%200.2019)
    * **R_Squared:** 0.50414716

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T4](../plots/net-stew-stew_func_call_with_named_arg_T4.png)
![net-stew-stew T1](../plots/net-stew-stew_func_call_with_named_arg_T1.png)
![net-stew-stew T6](../plots/net-stew-stew_func_call_with_named_arg_T6.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-7.8e-05x%5E4%20&plus;%200.004607x%5E3%20&plus;-0.08435x%5E2%20&plus;%200.481551x%20&plus;%200.361966)
    * **R_Squared:** 0.64037329
* **Plateau Gradual Decline - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-0.411765%7D%7B1%20&plus;%20%5Cepsilon%5E%28-28.341228%28x%20-11.271143%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 0.21568627
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-2.025563x%5E%7B0.788438%7D%20&plus;%200.66788)
    * **R_Squared:** 0.0834827
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.001916x%20&plus;%200.777778)
    * **R_Squared:** 0.00127714
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B2.786475%7D%28x%29%20&plus;%200.75)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T11_4](../plots/net-stew-stew_property_delegation_T11_4.png)
![net-stew-stew T8](../plots/net-stew-stew_property_delegation_T8.png)
![net-stew-stew T5](../plots/net-stew-stew_property_delegation_T5.png)
![net-stew-stew T2](../plots/net-stew-stew_property_delegation_T2.png)
![net-stew-stew T6](../plots/net-stew-stew_property_delegation_T6.png)
### <a name="sealed_class">Sealed Class</a>
----
#### Functions
* **Plateau Sudden Decline - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-48.260579%28x%20-8.499708%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.000129x%5E4%20&plus;%200.006335x%5E3%20&plus;-0.099286x%5E2%20&plus;%200.461845x%20&plus;%200.496732)
    * **R_Squared:** 0.87058824
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.001433x%5E3%20&plus;-0.03827x%5E2%20&plus;%200.187335x%20&plus;%200.820261',))
    * **R_Squared:** 0.85526316
* **Sudden Decline - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?25.282527x%5E%7B0.958843%7D%20&plus;%20-1.543245)
    * **R_Squared:** 0.7534843
* **Constant Decline - Linear:** ![equation](http://latex.codecogs.com/svg.latex?-0.082559x%20&plus;%201.228758)
    * **R_Squared:** 0.74303406
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.0%5Clog_%7B544.699054%7D%28x%29%20&plus;%200.444444)
    * **R_Squared:** -0.0

**Plots** :chart_with_upwards_trend:
-----

![net-stew-stew T10](../plots/net-stew-stew_sealed_class_T10.png)
![net-stew-stew T11_4](../plots/net-stew-stew_sealed_class_T11_4.png)
![net-stew-stew T11_3](../plots/net-stew-stew_sealed_class_T11_3.png)
![net-stew-stew T5](../plots/net-stew-stew_sealed_class_T5.png)
![net-stew-stew T2](../plots/net-stew-stew_sealed_class_T2.png)
![net-stew-stew T6](../plots/net-stew-stew_sealed_class_T6.png)
