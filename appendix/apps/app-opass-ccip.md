## app-opass-ccip
----
#### Metrics provided by Detekt
* Number of lines of code 4188
* Number of Kotlin files: 69
* Cyclomatic complexity: 609
* Cyclomatic complexity by thousands of lines: 264 

----
**19** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#func_with_default_value">Function with Default Value</a> 
*	<a href="#singleton">Singleton</a> 
*	<a href="#range_expr">Range Expression</a> 
*	<a href="#smart_cast">Smart Cast</a> 
*	<a href="#data_class">Data Class</a> 
*	<a href="#func_call_with_named_arg">Function call with Named Argument</a> 
*	<a href="#extension_function">Extension Function</a> 
*	<a href="#property_delegation">Property Delegation</a> 
*	<a href="#destructuring_declaration">Destructuring Declaration</a> 
*	<a href="#coroutine">Coroutine</a> 
*	<a href="#sealed_class">Sealed Class</a> 
*	<a href="#super_delegation">Super Delegation</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-6e-05x%5E3%20&plus;0.016701x%5E2%20&plus;%20-0.653308x%20&plus;%20191.838236',))
    * **R_Squared:** 0.93192824
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.599347x%20&plus;%20171.617413)
    * **R_Squared:** 0.89131883
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-5422.030868x%5E%7B1.001138%7D%20&plus;%20-302.033888)
    * **R_Squared:** 0.89210211
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?31.050754%5Clog_%7B3.120034%7D%28x%29%20&plus;%20110.753037)
    * **R_Squared:** 0.62232277

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T11_3](../plots/app-opass-ccip_type_inference_T11_3.png)
![app-opass-ccip T1](../plots/app-opass-ccip_type_inference_T1.png)
![app-opass-ccip T4](../plots/app-opass-ccip_type_inference_T4.png)
![app-opass-ccip T6](../plots/app-opass-ccip_type_inference_T6.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-822.399792x%5E%7B1.005344%7D%20&plus;%20-53.551114)
    * **R_Squared:** 0.95518174
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.704099x%20&plus;%2017.407633)
    * **R_Squared:** 0.93787489
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?21.82019%5Clog_%7B3.012886%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.53488065

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T4](../plots/app-opass-ccip_lambda_T4.png)
![app-opass-ccip T1](../plots/app-opass-ccip_lambda_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-275.092831x%5E%7B1.009479%7D%20&plus;%20-11.647906)
    * **R_Squared:** 0.92151982
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.315214x%20&plus;%20-4.838491)
    * **R_Squared:** 0.8700332
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?7.419908%5Clog_%7B3.460292%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.36330071
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-21.125663%7D%7B1%20&plus;%20%5Cepsilon%5E%28--73.791256%28x%20-41.512937%29%29%7D%20&plus;%2028.369565)
    * **R_Squared:** 0.25845947

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T4](../plots/app-opass-ccip_safe_call_T4.png)
![app-opass-ccip T1](../plots/app-opass-ccip_safe_call_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_safe_call_T6.png)
![app-opass-ccip T9](../plots/app-opass-ccip_safe_call_T9.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-454.190613x%5E%7B1.005797%7D%20&plus;%20-8.668238)
    * **R_Squared:** 0.86039061
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.137564x%20&plus;%203.228172)
    * **R_Squared:** 0.84159327
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.001614%5Clog_%7B3.694967%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.47854628
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-9.81811%7D%7B1%20&plus;%20%5Cepsilon%5E%28--486.502224%28x%20-18.044891%29%29%7D%20&plus;%2016.596231)
    * **R_Squared:** 0.14523227

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T4](../plots/app-opass-ccip_when_expr_T4.png)
![app-opass-ccip T1](../plots/app-opass-ccip_when_expr_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_when_expr_T6.png)
![app-opass-ccip T9](../plots/app-opass-ccip_when_expr_T9.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.141171x%20&plus;%2021.998494)
    * **R_Squared:** 0.84370347
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-1841.647019x%5E%7B1.002164%7D%20&plus;%20-30.789683)
    * **R_Squared:** 0.84648771
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?7.434085%5Clog_%7B3.395118%7D%28x%29%20&plus;%209.117477)
    * **R_Squared:** 0.52738741

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T1](../plots/app-opass-ccip_unsafe_call_T1.png)
![app-opass-ccip T4](../plots/app-opass-ccip_unsafe_call_T4.png)
![app-opass-ccip T6](../plots/app-opass-ccip_unsafe_call_T6.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.006191x%20&plus;%2022.068483)
    * **R_Squared:** 0.06639565
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?134.290039x%5E%7B1.008753%7D%20&plus;%2021.874872)
    * **R_Squared:** 0.07132522
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.670455%7D%7B1%20&plus;%20%5Cepsilon%5E%28-42.157861%28x%20-3.20025%29%29%7D%20&plus;%2020.0)
    * **R_Squared:** 0.07623691
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.909323%5Clog_%7B27.388236%7D%28x%29%20&plus;%2021.469988)
    * **R_Squared:** 0.04402396

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T1](../plots/app-opass-ccip_companion_object_T1.png)
![app-opass-ccip T4](../plots/app-opass-ccip_companion_object_T4.png)
![app-opass-ccip T9](../plots/app-opass-ccip_companion_object_T9.png)
![app-opass-ccip T6](../plots/app-opass-ccip_companion_object_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B5.418196%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.055093%28x%20-73.322206%29%29%7D%20&plus;%202.685027)
    * **R_Squared:** 0.94402316
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.047886x%20&plus;%201.944367)
    * **R_Squared:** 0.91024094
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-396.86442x%5E%7B1.004911%7D%20&plus;%20-4.721141)
    * **R_Squared:** 0.91799647
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.743785%5Clog_%7B3.717643%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.59595376

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T7](../plots/app-opass-ccip_string_template_T7.png)
![app-opass-ccip T1](../plots/app-opass-ccip_string_template_T1.png)
![app-opass-ccip T4](../plots/app-opass-ccip_string_template_T4.png)
![app-opass-ccip T6](../plots/app-opass-ccip_string_template_T6.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.01472%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.163324%28x%20-102.600885%29%29%7D%20&plus;%201.967434)
    * **R_Squared:** 0.97261909
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-142.802271x%5E%7B1.005956%7D%20&plus;%20-0.912155)
    * **R_Squared:** 0.81756978
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.024232x%20&plus;%201.081727)
    * **R_Squared:** 0.79574257
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.998305%5Clog_%7B3.552199%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.45254269

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T7](../plots/app-opass-ccip_func_with_default_value_T7.png)
![app-opass-ccip T4](../plots/app-opass-ccip_func_with_default_value_T4.png)
![app-opass-ccip T1](../plots/app-opass-ccip_func_with_default_value_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_func_with_default_value_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-250.399097x%5E%7B1.005777%7D%20&plus;%20-1.792552)
    * **R_Squared:** 0.85065774
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.042017x%20&plus;%201.84979)
    * **R_Squared:** 0.83565913
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.796717%5Clog_%7B3.718381%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.54942155
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-3.721946%7D%7B1%20&plus;%20%5Cepsilon%5E%28--95.695981%28x%20-20.001991%29%29%7D%20&plus;%206.037736)
    * **R_Squared:** 0.2355098

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T4](../plots/app-opass-ccip_singleton_T4.png)
![app-opass-ccip T1](../plots/app-opass-ccip_singleton_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_singleton_T6.png)
![app-opass-ccip T7](../plots/app-opass-ccip_singleton_T7.png)
### <a name="range_expr">Range Expression</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-44.296002%28x%20-93.500002%29%29%7D%20&plus;%202.0)
    * **R_Squared:** 1.0
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.008367x%20&plus;%201.727387)
    * **R_Squared:** 0.7488764
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-1104.890186x%5E%7B1.001461%7D%20&plus;%20-3.258876)
    * **R_Squared:** 0.75015099
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.996566%5Clog_%7B14.79683%7D%28x%29%20&plus;%200.924426)
    * **R_Squared:** 0.49281068

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T9](../plots/app-opass-ccip_range_expr_T9.png)
![app-opass-ccip T1](../plots/app-opass-ccip_range_expr_T1.png)
![app-opass-ccip T4](../plots/app-opass-ccip_range_expr_T4.png)
![app-opass-ccip T6](../plots/app-opass-ccip_range_expr_T6.png)
### <a name="smart_cast">Smart Cast</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-7.1e-05x%5E3%20&plus;0.008229x%5E2%20&plus;%20-0.180022x%20&plus;%2012.163236',))
    * **R_Squared:** 0.74400742
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.06577x%20&plus;%2010.746161)
    * **R_Squared:** 0.59064614
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.819099%5Clog_%7B3.706776%7D%28x%29%20&plus;%208.692655)
    * **R_Squared:** 0.39141893

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T11_3](../plots/app-opass-ccip_smart_cast_T11_3.png)
![app-opass-ccip T1](../plots/app-opass-ccip_smart_cast_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_smart_cast_T6.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.105191x%20&plus;%2011.678049)
    * **R_Squared:** 0.80686587
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-5992.089932x%5E%7B1.000802%7D%20&plus;%20-110.107911)
    * **R_Squared:** 0.80720426
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?6.362204%5Clog_%7B3.551657%7D%28x%29%20&plus;%200.02677)
    * **R_Squared:** 0.61884469

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T1](../plots/app-opass-ccip_data_class_T1.png)
![app-opass-ccip T4](../plots/app-opass-ccip_data_class_T4.png)
![app-opass-ccip T6](../plots/app-opass-ccip_data_class_T6.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.663429%5Clog_%7B3.851918%7D%28x%29%20&plus;%205.012646)
    * **R_Squared:** 0.66195117
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.01663x%20&plus;%206.017164)
    * **R_Squared:** 0.50871542
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-0.393443%7D%7B1%20&plus;%20%5Cepsilon%5E%28--81.541016%28x%20-61.70763%29%29%7D%20&plus;%207.0)
    * **R_Squared:** 0.09682377

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T6](../plots/app-opass-ccip_func_call_with_named_arg_T6.png)
![app-opass-ccip T1](../plots/app-opass-ccip_func_call_with_named_arg_T1.png)
![app-opass-ccip T7](../plots/app-opass-ccip_func_call_with_named_arg_T7.png)
### <a name="extension_function">Extension Function</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-46.515915%28x%20-30.499944%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000941%5Clog_%7B12.222788%7D%28x%29%20&plus;%200.241871)
    * **R_Squared:** 0.66700827
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.011843x%20&plus;%201.089806)
    * **R_Squared:** 0.61581137

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T9](../plots/app-opass-ccip_extension_function_T9.png)
![app-opass-ccip T6](../plots/app-opass-ccip_extension_function_T6.png)
![app-opass-ccip T1](../plots/app-opass-ccip_extension_function_T1.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-52.894518x%5E%7B1.009272%7D%20&plus;%202.189709)
    * **R_Squared:** 0.61223851
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.036666x%20&plus;%203.068734)
    * **R_Squared:** 0.57800067
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.014924%5Clog_%7B3.762162%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.39417583

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T4](../plots/app-opass-ccip_property_delegation_T4.png)
![app-opass-ccip T1](../plots/app-opass-ccip_property_delegation_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_property_delegation_T6.png)
### <a name="destructuring_declaration">Destructuring Declaration</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.122836%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.071777%28x%20-111.97465%29%29%7D%20&plus;%205.963315)
    * **R_Squared:** 0.94932475
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?36.176862x%5E%7B1.008944%7D%20&plus;%204.974004)
    * **R_Squared:** 0.87032735
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.015197x%20&plus;%205.397652)
    * **R_Squared:** 0.82279661
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.829149%5Clog_%7B3.718282%7D%28x%29%20&plus;%204.1092)
    * **R_Squared:** 0.47831263

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T7](../plots/app-opass-ccip_destructuring_declaration_T7.png)
![app-opass-ccip T4](../plots/app-opass-ccip_destructuring_declaration_T4.png)
![app-opass-ccip T1](../plots/app-opass-ccip_destructuring_declaration_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_destructuring_declaration_T6.png)
### <a name="coroutine">Coroutine</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.242408%5Clog_%7B3.71713%7D%28x%29%20&plus;%206.252692)
    * **R_Squared:** 0.58436051
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.027483x%20&plus;%208.287901)
    * **R_Squared:** 0.51880343

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T6](../plots/app-opass-ccip_coroutine_T6.png)
![app-opass-ccip T1](../plots/app-opass-ccip_coroutine_T1.png)
### <a name="sealed_class">Sealed Class</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-49.292985%28x%20-32.499975%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.017414x%20&plus;%200.887082)
    * **R_Squared:** 0.71396698
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.980911%5Clog_%7B10.347645%7D%28x%29%20&plus;%200.163715)
    * **R_Squared:** 0.61691707

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T9](../plots/app-opass-ccip_sealed_class_T9.png)
![app-opass-ccip T1](../plots/app-opass-ccip_sealed_class_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_sealed_class_T6.png)
### <a name="super_delegation">Super Delegation</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.007304x%20&plus;%202.44546)
    * **R_Squared:** 0.13934666
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.695133%5Clog_%7B70.662953%7D%28x%29%20&plus;%202.226719)
    * **R_Squared:** 0.06737481

**Plots** :chart_with_upwards_trend:
-----

![app-opass-ccip T1](../plots/app-opass-ccip_super_delegation_T1.png)
![app-opass-ccip T6](../plots/app-opass-ccip_super_delegation_T6.png)
