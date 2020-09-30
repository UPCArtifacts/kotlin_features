## com-stevenschoen-putionew
----
#### Metrics provided by Detekt
* Number of lines of code 5863
* Number of Kotlin files: 55
* Cyclomatic complexity: 856
* Cyclomatic complexity by thousands of lines: 254 

----
**16** features analyzed

*	<a href="#type_inference">Type Inference</a> 
*	<a href="#lambda">Lambda</a> 
*	<a href="#safe_call">Safe Call</a> 
*	<a href="#when_expr">When expression</a> 
*	<a href="#unsafe_call">Unsafe Call</a> 
*	<a href="#companion_object">Companion Object</a> 
*	<a href="#string_template">String Template</a> 
*	<a href="#func_with_default_value">Function with Default Value</a> 
*	<a href="#range_expr">Range Expression</a> 
*	<a href="#smart_cast">Smart Cast</a> 
*	<a href="#data_class">Data Class</a> 
*	<a href="#func_call_with_named_arg">Function call with Named Argument</a> 
*	<a href="#extension_function">Extension Function</a> 
*	<a href="#property_delegation">Property Delegation</a> 
*	<a href="#destructuring_declaration">Destructuring Declaration</a> 
*	<a href="#sealed_class">Sealed Class</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.000245x%5E3%20&plus;-0.071883x%5E2%20&plus;%206.999611x%20&plus;%20192.146115',))
    * **R_Squared:** 0.95500963
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?63.837867%5Clog_%7B2.727913%7D%28x%29%20&plus;%20130.742333)
    * **R_Squared:** 0.91750294
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?1.479488x%20&plus;%20283.470532)
    * **R_Squared:** 0.70725281

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T11_3](../plots/com-stevenschoen-putionew_type_inference_T11_3.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_type_inference_T6.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_type_inference_T1.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?1.458032x%20&plus;%20134.355643)
    * **R_Squared:** 0.88799446
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?54.818053%5Clog_%7B2.76225%7D%28x%29%20&plus;%2017.31973)
    * **R_Squared:** 0.85322313

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_lambda_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B38.289096%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.076467%28x%20-27.640606%29%29%7D%20&plus;%2037.555384)
    * **R_Squared:** 0.94582186
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?13.046422%5Clog_%7B3.075003%7D%28x%29%20&plus;%2022.346972)
    * **R_Squared:** 0.89482304
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.282363x%20&plus;%2049.478932)
    * **R_Squared:** 0.75370429

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T7](../plots/com-stevenschoen-putionew_safe_call_T7.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_safe_call_T6.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_safe_call_T1.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?8.317909%5Clog_%7B3.38256%7D%28x%29%20&plus;%208.619902)
    * **R_Squared:** 0.92955875
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.163399x%20&plus;%2024.721447)
    * **R_Squared:** 0.75915126

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_when_expr_T6.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_when_expr_T1.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.870346x%20&plus;%20130.236011)
    * **R_Squared:** 0.84921064
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?33.769035%5Clog_%7B2.783821%7D%28x%29%20&plus;%2057.395401)
    * **R_Squared:** 0.8558211
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-42.526617%7D%7B1%20&plus;%20%5Cepsilon%5E%28--313.988997%28x%20-111.162739%29%29%7D%20&plus;%20222.454546)
    * **R_Squared:** 0.13410344

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_unsafe_call_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_unsafe_call_T6.png)
![com-stevenschoen-putionew T9](../plots/com-stevenschoen-putionew_unsafe_call_T9.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.731341%5Clog_%7B3.411369%7D%28x%29%20&plus;%2011.827136)
    * **R_Squared:** 0.84702357
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.097158x%20&plus;%2023.746105)
    * **R_Squared:** 0.52232746

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_companion_object_T6.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_companion_object_T1.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B5.401802%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.358363%28x%20-57.754182%29%29%7D%20&plus;%208.543081)
    * **R_Squared:** 0.96141555
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.068072x%20&plus;%207.223411)
    * **R_Squared:** 0.83717513
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?3.04423%5Clog_%7B3.718368%7D%28x%29%20&plus;%202.5289)
    * **R_Squared:** 0.68121215

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T7](../plots/com-stevenschoen-putionew_string_template_T7.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_string_template_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_string_template_T6.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.050393x%20&plus;%205.310663)
    * **R_Squared:** 0.83259004
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.498799%5Clog_%7B3.718282%7D%28x%29%20&plus;%201.119957)
    * **R_Squared:** 0.83294272

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_func_with_default_value_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_func_with_default_value_T6.png)
### <a name="range_expr">Range Expression</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-46.315556%28x%20-35.499964%29%29%7D%20&plus;%202.0)
    * **R_Squared:** 1.0
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.014267x%20&plus;%201.940077)
    * **R_Squared:** 0.69196429
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.999963%5Clog_%7B11.186104%7D%28x%29%20&plus;%201.145126)
    * **R_Squared:** 0.63190136

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T9](../plots/com-stevenschoen-putionew_range_expr_T9.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_range_expr_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_range_expr_T6.png)
### <a name="smart_cast">Smart Cast</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.184723x%20&plus;%204.598564)
    * **R_Squared:** 0.92152126
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.558731%5Clog_%7B3.666665%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.65135665
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-10.961153%7D%7B1%20&plus;%20%5Cepsilon%5E%28--59.869073%28x%20-8.060474%29%29%7D%20&plus;%2016.675439)
    * **R_Squared:** 0.15927323

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_smart_cast_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_smart_cast_T6.png)
![com-stevenschoen-putionew T9](../plots/com-stevenschoen-putionew_smart_cast_T9.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?7.686653x%5E%7B1.014696%7D%20&plus;%200.276739)
    * **R_Squared:** 0.87533257
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B8.82668%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.02763%28x%20-120.344665%29%29%7D%20&plus;%200.970128)
    * **R_Squared:** 0.8761722
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.034604x%20&plus;%200.642325)
    * **R_Squared:** 0.83276732
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.001665%5Clog_%7B3.844172%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.52045443

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T4](../plots/com-stevenschoen-putionew_data_class_T4.png)
![com-stevenschoen-putionew T7](../plots/com-stevenschoen-putionew_data_class_T7.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_data_class_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_data_class_T6.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.044134x%20&plus;%203.925078)
    * **R_Squared:** 0.91493669
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.011904%5Clog_%7B3.718394%7D%28x%29%20&plus;%200.770045)
    * **R_Squared:** 0.77355959

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_func_call_with_named_arg_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_func_call_with_named_arg_T6.png)
### <a name="extension_function">Extension Function</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.43771%7D%7B1%20&plus;%20%5Cepsilon%5E%28-24.327414%28x%20-12.071568%29%29%7D%20&plus;%201.636364)
    * **R_Squared:** 0.90123457
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('6.8e-05x%5E3%20&plus;-0.008531x%5E2%20&plus;%200.32543x%20&plus;%200.333431',))
    * **R_Squared:** 0.76277162
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.110921%5Clog_%7B3.718283%7D%28x%29%20&plus;%200.899512)
    * **R_Squared:** 0.60403747
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.029058x%20&plus;%202.662937)
    * **R_Squared:** 0.31963261

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T9](../plots/com-stevenschoen-putionew_extension_function_T9.png)
![com-stevenschoen-putionew T11_3](../plots/com-stevenschoen-putionew_extension_function_T11_3.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_extension_function_T6.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_extension_function_T1.png)
### <a name="property_delegation">Property Delegation</a>
----
#### Functions
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.955761%5Clog_%7B3.460425%7D%28x%29%20&plus;%205.930489)
    * **R_Squared:** 0.83236013
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.121181x%20&plus;%2016.858827)
    * **R_Squared:** 0.7567629

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_property_delegation_T6.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_property_delegation_T1.png)
### <a name="destructuring_declaration">Destructuring Declaration</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B2.483161%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.058777%28x%20-36.645787%29%29%7D%20&plus;%201.609703)
    * **R_Squared:** 0.91285782
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.020275x%20&plus;%202.077273)
    * **R_Squared:** 0.81931077
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.000611%5Clog_%7B3.856326%7D%28x%29%20&plus;%200.479714)
    * **R_Squared:** 0.78080272

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T7](../plots/com-stevenschoen-putionew_destructuring_declaration_T7.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_destructuring_declaration_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_destructuring_declaration_T6.png)
### <a name="sealed_class">Sealed Class</a>
----
#### Functions
* **Plateau Sudden Rise - Binary Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B1.0%7D%7B1%20&plus;%20%5Cepsilon%5E%28-48.357014%28x%20-24.499966%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 1.0
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.018913x%20&plus;%200.981087)
    * **R_Squared:** 0.67142857
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?0.875262%5Clog_%7B7.919875%7D%28x%29%20&plus;%200.263812)
    * **R_Squared:** 0.65360949

**Plots** :chart_with_upwards_trend:
-----

![com-stevenschoen-putionew T9](../plots/com-stevenschoen-putionew_sealed_class_T9.png)
![com-stevenschoen-putionew T1](../plots/com-stevenschoen-putionew_sealed_class_T1.png)
![com-stevenschoen-putionew T6](../plots/com-stevenschoen-putionew_sealed_class_T6.png)
