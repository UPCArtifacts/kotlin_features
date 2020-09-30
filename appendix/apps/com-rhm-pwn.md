## com-rhm-pwn
----
#### Metrics provided by Detekt
* Number of lines of code 1495
* Number of Kotlin files: 23
* Cyclomatic complexity: 191
* Cyclomatic complexity by thousands of lines: 232 

----
**13** features analyzed

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
*	<a href="#sealed_class">Sealed Class</a> 


### <a name="type_inference">Type Inference</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-56.625874%7D%7B1%20&plus;%20%5Cepsilon%5E%28--40.943196%28x%20-26.960318%29%29%7D%20&plus;%2060.318182)
    * **R_Squared:** 0.9622561
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.004034x%5E3%20&plus;0.313416x%5E2%20&plus;%20-4.80137x%20&plus;%2015.081349',))
    * **R_Squared:** 0.94137425
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-5.7e-05x%5E4%20&plus;%200.001628x%5E3%20&plus;0.130179x%5E2%20&plus;%20-2.71665x%20&plus;%209.397159)
    * **R_Squared:** 0.94428388
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?1.854796x%20&plus;%20-16.288265)
    * **R_Squared:** 0.83922187
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-646.109689x%5E%7B1.008126%7D%20&plus;%20-199.842896)
    * **R_Squared:** 0.84240907
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?13.564672%5Clog_%7B3.287188%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.3980543

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T7](../plots/com-rhm-pwn_type_inference_T7.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_type_inference_T11_3.png)
![com-rhm-pwn T11_4](../plots/com-rhm-pwn_type_inference_T11_4.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_type_inference_T1.png)
![com-rhm-pwn T4](../plots/com-rhm-pwn_type_inference_T4.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_type_inference_T6.png)
### <a name="lambda">Lambda</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-24.175047%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.600586%28x%20-25.558154%29%29%7D%20&plus;%2025.099625)
    * **R_Squared:** 0.99315738
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.001755x%5E3%20&plus;0.132319x%5E2%20&plus;%20-1.977713x%20&plus;%206.668517',))
    * **R_Squared:** 0.9408097
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.740502x%20&plus;%20-5.663121)
    * **R_Squared:** 0.8324569
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-751.763063x%5E%7B1.006185%7D%20&plus;%20-107.831762)
    * **R_Squared:** 0.83424075
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?6.022472%5Clog_%7B3.586033%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.41451493

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T7](../plots/com-rhm-pwn_lambda_T7.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_lambda_T11_3.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_lambda_T1.png)
![com-rhm-pwn T4](../plots/com-rhm-pwn_lambda_T4.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_lambda_T6.png)
### <a name="safe_call">Safe Call</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B3.941176%7D%7B1%20&plus;%20%5Cepsilon%5E%28-20.351549%28x%20-7.053008%29%29%7D%20&plus;%201.0)
    * **R_Squared:** 0.96095783
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?0.000253x%5E4%20&plus;%20-0.012803x%5E3%20&plus;0.195091x%5E2%20&plus;%20-0.629797x%20&plus;%201.337285)
    * **R_Squared:** 0.85627118
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.230382%5Clog_%7B3.717935%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.7149634
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.203478x%20&plus;%201.289855)
    * **R_Squared:** 0.63204309

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T7](../plots/com-rhm-pwn_safe_call_T7.png)
![com-rhm-pwn T11_4](../plots/com-rhm-pwn_safe_call_T11_4.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_safe_call_T6.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_safe_call_T1.png)
### <a name="when_expr">When expression</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B5.773853%7D%7B1%20&plus;%20%5Cepsilon%5E%28-1.441611%28x%20-24.532531%29%29%7D%20&plus;%200.976376)
    * **R_Squared:** 0.98921433
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.000493x%5E3%20&plus;0.036304x%5E2%20&plus;%20-0.542883x%20&plus;%202.567536',))
    * **R_Squared:** 0.9178756
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-131.887286x%5E%7B1.016171%7D%20&plus;%20-8.593073)
    * **R_Squared:** 0.82218235
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.194598x%20&plus;%20-0.809091)
    * **R_Squared:** 0.81192507
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.79925%5Clog_%7B3.714076%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.44074833

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T7](../plots/com-rhm-pwn_when_expr_T7.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_when_expr_T11_3.png)
![com-rhm-pwn T4](../plots/com-rhm-pwn_when_expr_T4.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_when_expr_T1.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_when_expr_T6.png)
### <a name="unsafe_call">Unsafe Call</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.001017x%5E3%20&plus;-0.053745x%5E2%20&plus;%200.880403x%20&plus;%205.64359',))
    * **R_Squared:** 0.81728875
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.364593%5Clog_%7B3.708464%7D%28x%29%20&plus;%207.066067)
    * **R_Squared:** 0.65044764
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.084249x%20&plus;%208.376068)
    * **R_Squared:** 0.37912088

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T11_3](../plots/com-rhm-pwn_unsafe_call_T11_3.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_unsafe_call_T6.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_unsafe_call_T1.png)
### <a name="companion_object">Companion Object</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B7.018811%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.984962%28x%20-25.356152%29%29%7D%20&plus;%201.031406)
    * **R_Squared:** 0.9912083
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.000508x%5E3%20&plus;0.038178x%5E2%20&plus;%20-0.559961x%20&plus;%202.553522',))
    * **R_Squared:** 0.91059333
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.213367x%20&plus;%20-0.844388)
    * **R_Squared:** 0.79920322
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.147134%5Clog_%7B3.714437%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.45760337

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T7](../plots/com-rhm-pwn_companion_object_T7.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_companion_object_T11_3.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_companion_object_T1.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_companion_object_T6.png)
### <a name="string_template">String Template</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-18.353022%7D%7B1%20&plus;%20%5Cepsilon%5E%28--0.388839%28x%20-29.299306%29%29%7D%20&plus;%2020.743752)
    * **R_Squared:** 0.96836023
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-6.9e-05x%5E4%20&plus;%200.005439x%5E3%20&plus;-0.114688x%5E2%20&plus;%200.799595x%20&plus;%200.660874)
    * **R_Squared:** 0.96095198
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-53.518678x%5E%7B1.035752%7D%20&plus;%20-7.27994)
    * **R_Squared:** 0.87856506
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.545323x%20&plus;%20-3.542424)
    * **R_Squared:** 0.8316474
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?4.444983%5Clog_%7B3.671673%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.39869338

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T7](../plots/com-rhm-pwn_string_template_T7.png)
![com-rhm-pwn T11_4](../plots/com-rhm-pwn_string_template_T11_4.png)
![com-rhm-pwn T4](../plots/com-rhm-pwn_string_template_T4.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_string_template_T1.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_string_template_T6.png)
### <a name="func_with_default_value">Function with Default Value</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B5.307851%7D%7B1%20&plus;%20%5Cepsilon%5E%28-0.624164%28x%20-25.459504%29%29%7D%20&plus;%201.987686)
    * **R_Squared:** 0.97409963
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.000405x%5E3%20&plus;0.030053x%5E2%20&plus;%20-0.440494x%20&plus;%203.226415',))
    * **R_Squared:** 0.90517485
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.156122x%20&plus;%200.688776)
    * **R_Squared:** 0.77595465
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?2.108604%5Clog_%7B3.719087%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.53127221

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T7](../plots/com-rhm-pwn_func_with_default_value_T7.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_func_with_default_value_T11_3.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_func_with_default_value_T1.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_func_with_default_value_T6.png)
### <a name="singleton">Singleton</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.0007x%5E4%20&plus;%200.039265x%5E3%20&plus;-0.756714x%5E2%20&plus;%205.644242x%20&plus;%20-3.981277)
    * **R_Squared:** 0.94583593
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.005686x%5E3%20&plus;-0.230939x%5E2%20&plus;%202.696464x%20&plus;%200.22812',))
    * **R_Squared:** 0.73612561
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.585973%5Clog_%7B3.717673%7D%28x%29%20&plus;%205.289953)
    * **R_Squared:** 0.24356082
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.059289x%20&plus;%207.288538)
    * **R_Squared:** 0.03952569

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T11_4](../plots/com-rhm-pwn_singleton_T11_4.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_singleton_T11_3.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_singleton_T6.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_singleton_T1.png)
### <a name="smart_cast">Smart Cast</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.00068x%5E4%20&plus;%200.039994x%5E3%20&plus;-0.838147x%5E2%20&plus;%207.360101x%20&plus;%20-5.789474)
    * **R_Squared:** 0.97277758
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.007373x%5E3%20&plus;-0.327373x%5E2%20&plus;%204.496423x%20&plus;%20-1.700169',))
    * **R_Squared:** 0.92786592
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.603757%5Clog_%7B3.481642%7D%28x%29%20&plus;%205.051482)
    * **R_Squared:** 0.76447769
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.407115x%20&plus;%2010.245059)
    * **R_Squared:** 0.42291364

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T11_4](../plots/com-rhm-pwn_smart_cast_T11_4.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_smart_cast_T11_3.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_smart_cast_T6.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_smart_cast_T1.png)
### <a name="data_class">Data Class</a>
----
#### Functions
* **Plateau Gradual Rise - Sigmoid:** ![equation](http://latex.codecogs.com/svg.latex?%5Cfrac%7B-22.943168%7D%7B1%20&plus;%20%5Cepsilon%5E%28--1.593223%28x%20-29.072226%29%29%7D%20&plus;%2024.210754)
    * **R_Squared:** 0.99715684
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-5.1e-05x%5E4%20&plus;%200.003794x%5E3%20&plus;-0.054196x%5E2%20&plus;%20-0.014617x%20&plus;%202.057871)
    * **R_Squared:** 0.91336941
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('-0.001441x%5E3%20&plus;0.11861x%5E2%20&plus;%20-2.019043x%20&plus;%207.623296',))
    * **R_Squared:** 0.89457034
* **Sudden Rise - Exponential:** ![equation](http://latex.codecogs.com/svg.latex?-196.067092x%5E%7B1.016802%7D%20&plus;%20-30.185125)
    * **R_Squared:** 0.794289
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.678175x%20&plus;%20-6.193469)
    * **R_Squared:** 0.78091067
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?5.371656%5Clog_%7B3.631519%7D%28x%29%20&plus;%200.0)
    * **R_Squared:** 0.35237582

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T7](../plots/com-rhm-pwn_data_class_T7.png)
![com-rhm-pwn T11_4](../plots/com-rhm-pwn_data_class_T11_4.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_data_class_T11_3.png)
![com-rhm-pwn T4](../plots/com-rhm-pwn_data_class_T4.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_data_class_T1.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_data_class_T6.png)
### <a name="func_call_with_named_arg">Function call with Named Argument</a>
----
#### Functions
* **Instability - Polinomial 4:** ![equation](http://latex.codecogs.com/svg.latex?-0.00037x%5E4%20&plus;%200.022929x%5E3%20&plus;-0.512632x%5E2%20&plus;%204.921777x%20&plus;%20-4.418314)
    * **R_Squared:** 0.93710768
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.004433x%5E3%20&plus;-0.211147x%5E2%20&plus;%203.164657x%20&plus;%20-1.821476',))
    * **R_Squared:** 0.90957778
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?4.891134%5Clog_%7B3.658413%7D%28x%29%20&plus;%202.641886)
    * **R_Squared:** 0.7985124
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.345217x%20&plus;%206.934783)
    * **R_Squared:** 0.48513736

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T11_4](../plots/com-rhm-pwn_func_call_with_named_arg_T11_4.png)
![com-rhm-pwn T11_3](../plots/com-rhm-pwn_func_call_with_named_arg_T11_3.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_func_call_with_named_arg_T6.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_func_call_with_named_arg_T1.png)
### <a name="sealed_class">Sealed Class</a>
----
#### Functions
* **Instability - Polinomial 3:** ![equation](http://latex.codecogs.com/svg.latex?('0.001096x%5E3%20&plus;-0.050927x%5E2%20&plus;%200.767021x%20&plus;%202.231032',))
    * **R_Squared:** 0.85059652
* **Sudden Rise Plateau - Logarithm:** ![equation](http://latex.codecogs.com/svg.latex?1.400785%5Clog_%7B3.718268%7D%28x%29%20&plus;%203.104466)
    * **R_Squared:** 0.83015134
* **Constant Rise - Linear:** ![equation](http://latex.codecogs.com/svg.latex?0.109543x%20&plus;%204.194805)
    * **R_Squared:** 0.54617751

**Plots** :chart_with_upwards_trend:
-----

![com-rhm-pwn T11_3](../plots/com-rhm-pwn_sealed_class_T11_3.png)
![com-rhm-pwn T6](../plots/com-rhm-pwn_sealed_class_T6.png)
![com-rhm-pwn T1](../plots/com-rhm-pwn_sealed_class_T1.png)
